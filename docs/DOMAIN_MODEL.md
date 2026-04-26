# Domain Model
## Bello Beauty Academy Platform

**Document Version:** 1.0
**Date:** March 2026
**Status:** Draft

---

## Table of Contents

1. [Entity Summary Table](#1-entity-summary-table)
2. [Domain Entities](#2-domain-entities)
3. [Business Rules](#3-business-rules)

---

## 1. Entity Summary Table

The table below provides a concise summary of the seven core domain entities, their key attributes, primary methods, and relationships. Full detail for each entity is provided in Section 2.

| Entity | Attributes | Methods | Relationships |
|--------|-----------|---------|---------------|
| `User` | `userId`, `email`, `passwordHash`, `role`, `accountStatus` | `register()`, `login()`, `verifyEmail()`, `deactivate()` | Generalised by `Student`, `Trainer`, `Administrator` |
| `Student` | `studentId`, `phoneNumber`, `dateOfBirth`, `emergencyContact` | `browseCourses()`, `submitEnrollment()`, `downloadCertificate()` | Inherits `User`; has many `Enrollment`, many `Progress` |
| `Trainer` | `trainerId`, `specialisation`, `bio`, `isAvailable` | `recordAttendance()`, `submitAssessment()`, `uploadCourseMaterial()` | Inherits `User`; assigned to many `Course`, many `Session` |
| `Course` | `courseId`, `title`, `category`, `cost`, `maxCapacity`, `status` | `publish()`, `archive()`, `checkAvailability()` | Has many `Enrollment`, `Session`, `CourseMaterial`; assigned to one `Trainer` |
| `Enrollment` | `enrollmentId`, `studentId`, `courseId`, `status`, `appliedAt` | `activate()`, `complete()`, `suspend()`, `isEligibleForCertificate()` | Belongs to `Student` and `Course`; composed of `Payment`; produces `Certificate` |
| `Payment` | `paymentId`, `enrollmentId`, `method`, `status`, `proofOfPaymentUrl` | `uploadProof()`, `confirm()`, `reject()` | Composed within `Enrollment`; confirmed by `Administrator` |
| `Certificate` | `certificateId`, `enrollmentId`, `certificateNumber`, `issueDate`, `pdfUrl` | `generate()`, `issue()`, `download()`, `verify()` | Composed within `Enrollment`; belongs to `Student` and `Course` |

---

## 2. Domain Entities

The domain model identifies seven core entities that together represent the full operational scope of the Bello Beauty Academy Platform. Each entity captures the data it owns, the behaviours it is responsible for, and how it relates to other entities in the system.

---

### 2.1 User

The `User` entity is the base representation of any person who has an account on the platform. All three roles in the system (Student, Trainer, and Administrator) share a common set of identity and authentication attributes. Role-specific behaviour is handled through specialisation.

| Attribute | Type | Description |
|-----------|------|-------------|
| `userId` | `String` | Unique system-generated identifier |
| `firstName` | `String` | User's first name |
| `lastName` | `String` | User's last name |
| `email` | `String` | Unique email address used for login and notifications |
| `passwordHash` | `String` | bcrypt-hashed password, never stored in plain text |
| `role` | `Enum` | One of: `STUDENT`, `TRAINER`, `ADMIN` |
| `accountStatus` | `Enum` | One of: `REGISTERED`, `ACTIVE`, `SUSPENDED`, `DEACTIVATED` |
| `createdAt` | `DateTime` | Timestamp of account creation |
| `lastLoginAt` | `DateTime` | Timestamp of most recent login |

| Method | Description |
|--------|-------------|
| `register()` | Creates a new user account and sets status to `REGISTERED` |
| `verifyEmail()` | Validates the email verification token and sets status to `ACTIVE` |
| `login()` | Authenticates credentials and issues a JWT token |
| `resetPassword()` | Initiates a password reset flow |
| `updateProfile()` | Updates personal details such as name or contact information |
| `deactivate()` | Sets account status to `DEACTIVATED` |

**Relationships:**
- Generalised by `Student`, `Trainer`, and `Administrator`

---

### 2.2 Student

`Student` extends `User` and adds attributes and behaviours specific to a learner enrolled in training courses.

| Attribute | Type | Description |
|-----------|------|-------------|
| `studentId` | `String` | Academy-assigned student reference number |
| `phoneNumber` | `String` | Contact number for scheduling notifications |
| `dateOfBirth` | `Date` | Student's date of birth |
| `emergencyContact` | `String` | Name and number of emergency contact person |

| Method | Description |
|--------|-------------|
| `browseCourses()` | Retrieves the list of active, available courses |
| `submitEnrollment()` | Submits an enrollment application for a selected course |
| `uploadProofOfPayment()` | Uploads a proof of payment document for a pending enrollment |
| `viewSchedule()` | Retrieves the student's personal session timetable |
| `viewProgress()` | Retrieves attendance records and assessment results |
| `downloadCertificate()` | Downloads the PDF certificate for a completed course |

**Relationships:**
- Inherits from `User`
- Has many `Enrollment` records
- Has many `Progress` records

---

### 2.3 Trainer

`Trainer` extends `User` and represents a qualified beauty professional employed by the academy to deliver training sessions.

| Attribute | Type | Description |
|-----------|------|-------------|
| `trainerId` | `String` | Academy-assigned trainer reference number |
| `specialisation` | `String` | Area of expertise (e.g., Lash, Brow, Nail, Makeup) |
| `bio` | `String` | Short professional biography displayed on the platform |
| `isAvailable` | `Boolean` | Indicates whether the trainer is currently available for session assignment |

| Method | Description |
|--------|-------------|
| `viewAssignedSessions()` | Retrieves all sessions currently assigned to the trainer |
| `recordAttendance()` | Marks attendance for each enrolled student in a session |
| `submitAssessment()` | Saves a competency assessment result for a student |
| `uploadCourseMaterial()` | Attaches a document or resource file to an assigned course |

**Relationships:**
- Inherits from `User`
- Assigned to many `Session` records
- Assigned to many `Course` records

---

### 2.4 Course

`Course` represents a single training programme offered by the academy. Each course belongs to a category and has a defined structure, cost, and capacity.

| Attribute | Type | Description |
|-----------|------|-------------|
| `courseId` | `String` | Unique course identifier |
| `title` | `String` | Full name of the course |
| `category` | `Enum` | One of: `LASH`, `BROW`, `NAIL`, `MAKEUP` |
| `description` | `String` | Detailed course overview |
| `durationDays` | `Integer` | Number of training days required |
| `prerequisites` | `String` | Any prior course or skill requirements |
| `cost` | `Decimal` | Course fee in South African Rand |
| `maxCapacity` | `Integer` | Maximum number of students that can be enrolled |
| `currentEnrollments` | `Integer` | Count of currently active enrollments |
| `status` | `Enum` | One of: `DRAFT`, `ACTIVE`, `FULL`, `SUSPENDED`, `ARCHIVED` |
| `createdAt` | `DateTime` | Date the course record was created |

| Method | Description |
|--------|-------------|
| `publish()` | Changes course status from `DRAFT` to `ACTIVE` |
| `suspend()` | Temporarily takes the course offline |
| `archive()` | Permanently deactivates the course |
| `checkAvailability()` | Returns whether the course has remaining capacity |
| `getEnrolledStudents()` | Returns the list of actively enrolled students |

**Relationships:**
- Has many `Enrollment` records
- Has many `Session` records (aggregation)
- Has many `CourseMaterial` records (aggregation)
- Assigned to one `Trainer`

---

### 2.5 Enrollment

`Enrollment` is the central joining entity between a `Student` and a `Course`. It tracks the full lifecycle of a student's participation in a course, from application through to completion.

| Attribute | Type | Description |
|-----------|------|-------------|
| `enrollmentId` | `String` | Unique enrollment identifier |
| `studentId` | `String` | Reference to the enrolled student |
| `courseId` | `String` | Reference to the course |
| `status` | `Enum` | One of: `PENDING`, `AWAITING_PAYMENT`, `ACTIVE`, `IN_PROGRESS`, `COMPLETED`, `SUSPENDED`, `WITHDRAWN`, `REJECTED` |
| `appliedAt` | `DateTime` | Date and time the application was submitted |
| `activatedAt` | `DateTime` | Date the enrollment was activated after payment confirmation |
| `completedAt` | `DateTime` | Date the student completed all course requirements |

| Method | Description |
|--------|-------------|
| `activate()` | Sets enrollment status to `ACTIVE` after payment is confirmed |
| `complete()` | Sets enrollment status to `COMPLETED` when all requirements are met |
| `suspend()` | Pauses the enrollment due to attendance issues |
| `withdraw()` | Permanently removes the student from the course |
| `isEligibleForCertificate()` | Checks whether attendance and assessments meet the completion threshold |

**Relationships:**
- Belongs to one `Student`
- Belongs to one `Course`
- Has one `Payment` (composition)
- Has many `Progress` records
- Has one `Certificate` upon completion (composition)

---

### 2.6 Payment

`Payment` tracks the financial transaction associated with a single enrollment. The current system supports cash and EFT payments confirmed manually by an administrator.

| Attribute | Type | Description |
|-----------|------|-------------|
| `paymentId` | `String` | Unique payment identifier |
| `enrollmentId` | `String` | Reference to the associated enrollment |
| `method` | `Enum` | One of: `CASH`, `EFT` |
| `status` | `Enum` | One of: `PENDING_UPLOAD`, `UNDER_REVIEW`, `CONFIRMED`, `REJECTED` |
| `amount` | `Decimal` | Expected payment amount in South African Rand |
| `proofOfPaymentUrl` | `String` | AWS S3 URL of the uploaded proof of payment document |
| `uploadedAt` | `DateTime` | Timestamp of when the proof of payment was uploaded |
| `confirmedAt` | `DateTime` | Timestamp of administrator confirmation |
| `confirmedBy` | `String` | User ID of the administrator who confirmed the payment |
| `rejectionReason` | `String` | Reason provided when a payment is rejected |

| Method | Description |
|--------|-------------|
| `uploadProof()` | Saves the proof of payment file URL and updates status to `UNDER_REVIEW` |
| `confirm()` | Records administrator confirmation and triggers enrollment activation |
| `reject()` | Records rejection reason and notifies the student to resubmit |
| `getStatus()` | Returns the current payment status |

**Relationships:**
- Belongs to one `Enrollment` (composition)
- Confirmed by one `Administrator`

---

### 2.7 Certificate

`Certificate` represents a digital PDF credential issued to a student upon successful completion of a course. Each certificate carries a unique reference number for verification purposes.

| Attribute | Type | Description |
|-----------|------|-------------|
| `certificateId` | `String` | Unique certificate identifier |
| `enrollmentId` | `String` | Reference to the completed enrollment |
| `studentId` | `String` | Reference to the certificate recipient |
| `courseId` | `String` | Reference to the completed course |
| `certificateNumber` | `String` | Unique human-readable certificate reference (e.g., `BBA-2026-0042`) |
| `issueDate` | `Date` | Date the certificate was officially issued |
| `pdfUrl` | `String` | AWS S3 URL of the generated PDF certificate |
| `issuedBy` | `String` | User ID of the administrator who triggered generation |

| Method | Description |
|--------|-------------|
| `generate()` | Calls the Certificate Generation Service to produce the branded PDF |
| `issue()` | Saves certificate metadata to the database and notifies the student |
| `download()` | Retrieves the PDF from AWS S3 and streams it to the student's browser |
| `verify()` | Validates a certificate number against the database record |

**Relationships:**
- Belongs to one `Enrollment` (composition)
- Belongs to one `Student`
- Belongs to one `Course`
- Issued by one `Administrator`

---

## 3. Business Rules

The following business rules define the constraints and policies that govern system behaviour. These rules are enforced at the application level and, where applicable, at the database level.

| Rule ID | Business Rule |
|---------|--------------|
| BR01 | A student may not access course materials or attend sessions until their enrollment status is `ACTIVE`. Enrollment is only activated after payment has been confirmed by an administrator. |
| BR02 | A course may not accept new enrollments once `currentEnrollments` equals `maxCapacity`. The course status automatically transitions to `FULL`. |
| BR03 | A certificate may only be generated for a student whose enrollment status is `COMPLETED`. Completion requires an attendance rate of at least 80% and all assessments passed. |
| BR04 | Each enrollment record has exactly one associated payment record. The payment record is created automatically at the time of enrollment submission. |
| BR05 | A student may not enroll in the same course more than once while an active, in-progress, or pending enrollment for that course already exists. |
| BR06 | A trainer may only be assigned to a session if their `isAvailable` flag is `true`. |
| BR07 | An enrollment whose attendance rate falls below 50% is automatically flagged for suspension. If the student does not respond within 14 days, the enrollment transitions to `WITHDRAWN`. |
| BR08 | A proof of payment document must be in PDF, JPG, or PNG format and must not exceed 5MB. Uploads that do not meet these criteria are rejected before reaching the storage service. |
# SYSTEM_REQUIREMENTS : Bello Beauty Academy Platform

**Document Version:** 1.0
**Date:** March 2026
**Assignment:** Assignment 4 — System Requirements Document (SRD)
**Status:** Final

> This document extends the requirements first established in [SPECIFICATION.md](./SPECIFICATION.md). It adds stakeholder-traced functional requirements with acceptance criteria and a comprehensive set of categorised non-functional requirements.

---

## Table of Contents

1. [Introduction](#1-introduction)
2. [Functional Requirements](#2-functional-requirements)
   - [2.1 Student Requirements](#21-student-requirements)
   - [2.2 Trainer Requirements](#22-trainer-requirements)
   - [2.3 Administrator Requirements](#23-administrator-requirements)
3. [Non-Functional Requirements](#3-non-functional-requirements)
   - [3.1 Usability](#31-usability)
   - [3.2 Deployability](#32-deployability)
   - [3.3 Maintainability](#33-maintainability)
   - [3.4 Scalability](#34-scalability)
   - [3.5 Security](#35-security)
   - [3.6 Performance](#36-performance)
4. [Requirements Traceability Matrix](#4-requirements-traceability-matrix)

---

## 1. Introduction

This System Requirements Document (SRD) provides a comprehensive, stakeholder-traced catalogue of the functional and non-functional requirements for the **Bello Beauty Academy Platform**. Requirements are directly derived from the stakeholder concerns and pain points identified in [STAKEHOLDER_ANALYSIS.md](./STAKEHOLDER_ANALYSIS.md).

Each functional requirement is written as an unambiguous, testable statement accompanied by an acceptance criterion — the measurable condition under which the requirement is considered satisfied. Non-functional requirements are organised by quality attribute category, each with a quantifiable target.

---

## 2. Functional Requirements

### 2.1 Student Requirements

---

**FR01 — Student Account Registration**
> **Stakeholder:** Student
> **Requirement:** The system shall allow a prospective student to register for a personal account by providing their full name, email address, and a password.
> **Acceptance Criteria:**
> - A registration form is presented to unauthenticated users.
> - On valid submission, a user record is created in the database with role `student`.
> - The password is stored as a bcrypt hash; plain-text passwords are never persisted.
> - A confirmation email is dispatched to the registered email address within 60 seconds.
> - Duplicate email addresses are rejected with a clear validation message.

---

**FR02 — Student Login**
> **Stakeholder:** Student
> **Requirement:** The system shall allow a registered student to authenticate using their email address and password, receiving a JWT access token on successful login.
> **Acceptance Criteria:**
> - Valid credentials produce a JWT with role `student` embedded in the payload.
> - Invalid credentials return an HTTP 401 response with a generic error message.
> - The JWT expires after 8 hours of inactivity, requiring re-authentication.

---

**FR03 — Course Catalogue Browsing**
> **Stakeholder:** Student, Academy Owner
> **Requirement:** The system shall allow any user to browse the full catalogue of available training courses, organised by category (Lash, Brow, Nail, Makeup).
> **Acceptance Criteria:**
> - All active courses are returned from `GET /api/courses` without requiring authentication.
> - Courses are filterable by category.
> - Each course listing displays the course name, category, duration, and cost.

---

**FR04 — Course Detail View**
> **Stakeholder:** Student
> **Requirement:** The system shall display a detailed information page for each training course, including course name, category, description, duration, prerequisites, and cost.
> **Acceptance Criteria:**
> - `GET /api/courses/:id` returns all course fields for any active course.
> - Courses marked as `inactive` by an administrator are not returned in public listings.
> - The detail view renders correctly on desktop and mobile viewports.

---

**FR05 — Course Enrollment Submission**
> **Stakeholder:** Student, Administrator
> **Requirement:** The system shall allow an authenticated student to submit an enrollment application for a selected course.
> **Acceptance Criteria:**
> - `POST /api/enrollments` creates an enrollment record with status `pending` and a linked payment record with status `pending`.
> - A student may not enroll in the same course twice; duplicate attempts return an HTTP 409 response.
> - An automated enrollment confirmation email is dispatched to the student within 60 seconds.

---

**FR06 — Proof of Payment Upload**
> **Stakeholder:** Student, Payment Processing Staff
> **Requirement:** The system shall allow an authenticated student to upload a proof of payment document (bank slip or EFT screenshot) against their pending enrollment.
> **Acceptance Criteria:**
> - Accepted file formats: JPEG, PNG, PDF. Maximum file size: 5 MB.
> - The uploaded file is stored in AWS S3 and the file path is recorded in the payment record.
> - The student receives confirmation that the POP has been received and is under review.
> - An automated notification is dispatched to the administrator indicating a new POP is awaiting review.

---

**FR07 — Personal Schedule Viewing**
> **Stakeholder:** Student
> **Requirement:** The system shall allow an authenticated student to view their personalised class schedule, displaying all upcoming sessions for their active enrollments, including session date, time, venue, and trainer name.
> **Acceptance Criteria:**
> - The schedule is only accessible once the student's enrollment status is `active` (payment confirmed).
> - Sessions are displayed in ascending chronological order.
> - Sessions scheduled within the next 7 days are visually highlighted.

---

**FR08 — Course Materials Access**
> **Stakeholder:** Student
> **Requirement:** The system shall allow an authenticated student to view and download course materials uploaded by their trainer for courses in which they are actively enrolled.
> **Acceptance Criteria:**
> - Materials are only accessible to students with an `active` enrollment in the corresponding course.
> - Supported file types for download: PDF, DOCX, PPTX, JPEG, PNG.
> - Materials are listed with filename, upload date, and uploader name.

---

**FR09 — Training Progress Visibility**
> **Stakeholder:** Student
> **Requirement:** The system shall allow an authenticated student to view their training progress, including a session-by-session attendance record and competency assessment results for each enrolled course.
> **Acceptance Criteria:**
> - Attendance status (Present, Absent, Late) is displayed for every session that has occurred.
> - Assessment results, where submitted by the trainer, are displayed alongside the corresponding session.
> - The progress view is updated within 1 hour of a trainer submitting new records.

---

**FR10 — Certificate Download**
> **Stakeholder:** Student, Regulatory / Certification Body
> **Requirement:** The system shall allow an authenticated student who has successfully completed a course to download their digital certificate in PDF format.
> **Acceptance Criteria:**
> - A certificate download link is only presented once the administrator has issued the certificate for that student and course combination.
> - The downloaded PDF contains the student's full name, course name, completion date, and a unique certificate number.
> - The PDF is formatted consistently using the academy's branded certificate template.
> - The certificate download link remains valid and persistent for the lifetime of the student's account.

---

**FR11 — Automated Enrollment Notification**
> **Stakeholder:** Student
> **Requirement:** The system shall send an automated email notification to a student confirming successful enrollment in a course.
> **Acceptance Criteria:**
> - The email is dispatched within 60 seconds of enrollment submission.
> - The email includes the course name, enrollment date, and instructions for submitting proof of payment.

---

**FR12 — Payment Status Visibility**
> **Stakeholder:** Student
> **Requirement:** The system shall allow an authenticated student to view the current payment status of each of their course enrollments (Pending, Confirmed, Rejected).
> **Acceptance Criteria:**
> - Payment status is visible on the student's enrollment dashboard.
> - If a payment is rejected, a prompt to resubmit proof of payment is displayed alongside the status.

---

### 2.2 Trainer Requirements

---

**FR13 — Trainer Authentication**
> **Stakeholder:** Trainer
> **Requirement:** The system shall allow a trainer to log in using their credentials and access a trainer-specific portal that displays only features relevant to their role.
> **Acceptance Criteria:**
> - Valid trainer credentials produce a JWT with role `trainer`.
> - The trainer portal does not expose student enrollment management, payment records, or administrative reporting.
> - Failed login attempts return an HTTP 401 response.

---

**FR14 — Session and Course Visibility**
> **Stakeholder:** Trainer
> **Requirement:** The system shall display to an authenticated trainer all courses they are assigned to and all upcoming and past training sessions associated with those courses.
> **Acceptance Criteria:**
> - Sessions are listed with date, time, venue, and enrolled student count.
> - Trainers only see sessions and courses to which they have been explicitly assigned by an administrator.

---

**FR15 — Student Attendance Recording**
> **Stakeholder:** Trainer, Administrator
> **Requirement:** The system shall allow an authenticated trainer to record the attendance status of each enrolled student for a given training session.
> **Acceptance Criteria:**
> - Attendance statuses available: Present, Absent, Late.
> - Attendance can be recorded or updated up to 24 hours after the session end time.
> - Submitted attendance records are immediately reflected on the student's progress dashboard.
> - Records are stored with a timestamp and the trainer's user ID for audit purposes.

---

**FR16 — Competency Assessment Submission**
> **Stakeholder:** Trainer, Student
> **Requirement:** The system shall allow an authenticated trainer to submit competency assessment results for each enrolled student in their assigned courses.
> **Acceptance Criteria:**
> - Assessment results include a score or competency rating and an optional free-text comment.
> - Submitted results are immediately visible to the student on their progress dashboard.
> - Records include the submitting trainer's user ID and submission timestamp.

---

**FR17 — Course Material Upload**
> **Stakeholder:** Trainer, Student
> **Requirement:** The system shall allow an authenticated trainer to upload course materials to a course they are assigned to.
> **Acceptance Criteria:**
> - Accepted file types: PDF, DOCX, PPTX. Maximum file size: 20 MB per file.
> - Uploaded materials are stored in AWS S3 and made available to enrolled students immediately.
> - Each material is listed with filename, uploader name, and upload timestamp.

---

### 2.3 Administrator Requirements

---

**FR18 — Course Management**
> **Stakeholder:** Administrator, Academy Owner
> **Requirement:** The system shall allow an authenticated administrator to create, edit, and deactivate training courses, including assigning each course to a category.
> **Acceptance Criteria:**
> - Course creation requires: name, category, description, duration, and cost.
> - Deactivated courses are removed from the public catalogue but remain in the admin dashboard.
> - Courses with active enrollments cannot be deleted; they can only be deactivated.

---

**FR19 — Trainer Profile Management**
> **Stakeholder:** Administrator
> **Requirement:** The system shall allow an authenticated administrator to create and manage trainer profiles, including assigning trainers to specific courses.
> **Acceptance Criteria:**
> - A trainer profile requires: full name, email address, and area of specialisation.
> - Assigning a trainer to a course makes all course sessions visible in the trainer's portal.
> - Deactivating a trainer profile prevents future login without removing historical records.

---

**FR20 — Enrollment Approval Management**
> **Stakeholder:** Administrator
> **Requirement:** The system shall allow an authenticated administrator to view all student enrollment applications and approve or reject them.
> **Acceptance Criteria:**
> - All enrollments are listed with student name, course name, enrollment date, and current status.
> - Status changes trigger an automated email notification to the student.

---

**FR21 — Class Schedule Management**
> **Stakeholder:** Administrator, Trainer, Student
> **Requirement:** The system shall allow an authenticated administrator to create and manage class schedules, assigning dates, times, venues, and trainers to each session.
> **Acceptance Criteria:**
> - A session requires: linked course, date, start time, end time, venue, and assigned trainer.
> - The system prevents double-booking the same trainer for overlapping session times.
> - Published sessions immediately appear on enrolled students' schedule views.

---

**FR22 — Certificate Generation and Issuance**
> **Stakeholder:** Administrator, Student, Regulatory / Certification Body
> **Requirement:** The system shall allow an authenticated administrator to generate and issue a digital certificate for a student who has successfully completed a course.
> **Acceptance Criteria:**
> - The certificate can only be generated once all assessments have been submitted for that enrollment.
> - The generated PDF contains: student full name, course name, completion date, academy logo, and a unique certificate number.
> - The PDF is stored in AWS S3 and a metadata record is created in the `certificates` table.
> - A "certificate ready" email is dispatched to the student containing a download link.

---

**FR23 — Operational Report Generation**
> **Stakeholder:** Administrator, Academy Owner
> **Requirement:** The system shall allow an authenticated administrator to generate operational reports covering student enrollment numbers, course completion rates, trainer assignments, and payment summaries.
> **Acceptance Criteria:**
> - Reports are filterable by date range and course category.
> - Report data reflects the current state of the database at generation time.
> - Reports can be exported in CSV format.
> - Report generation completes within 5 seconds for datasets covering up to 12 months.

---

**FR24 — Payment Dashboard**
> **Stakeholder:** Administrator, Payment Processing Staff
> **Requirement:** The system shall provide an authenticated administrator with a payment dashboard displaying all enrollments grouped by payment status.
> **Acceptance Criteria:**
> - The dashboard displays: student name, course, payment method, POP upload status, payment status, and action buttons.
> - Clicking "View POP" retrieves the uploaded file from S3 for immediate review.

---

**FR25 — Payment Confirmation and Rejection**
> **Stakeholder:** Administrator, Payment Processing Staff
> **Requirement:** The system shall allow an authenticated administrator to confirm or reject a student proof of payment submission.
> **Acceptance Criteria:**
> - Confirming a payment sets payment status to `confirmed`, sets enrollment status to `active`, and dispatches a confirmation email to the student.
> - Rejecting a payment sets payment status to `rejected` and dispatches a rejection email prompting the student to resubmit.
> - Every confirmation and rejection is logged with the administrator's user ID and a timestamp.

---

**FR26 — Manual Cash Payment Recording**
> **Stakeholder:** Administrator, Payment Processing Staff
> **Requirement:** The system shall allow an authenticated administrator to manually record a cash payment against a student enrollment.
> **Acceptance Criteria:**
> - Cash payment recording does not require a POP file upload.
> - On confirmation, enrollment status is updated to `active` and a confirmation email is dispatched to the student.
> - The record includes the administrator's user ID, amount recorded, and a timestamp.

---

**FR27 — Payment Confirmed Email Notification**
> **Stakeholder:** Student
> **Requirement:** The system shall send an automated email notification to a student when their payment has been confirmed by the administrator.
> **Acceptance Criteria:**
> - The email is dispatched within 60 seconds of payment confirmation.
> - The email confirms the course name and instructs the student to log in to view their schedule.

---

## 3. Non-Functional Requirements

### 3.1 Usability

| ID | Requirement | Measurable Criterion |
|----|-------------|----------------------|
| NFR-U01 | The user interface shall be navigable by a non-technical user without requiring prior training or a user manual. | A first-time user can locate and enroll in a course within 5 minutes during user testing. |
| NFR-U02 | The system shall be fully responsive and render correctly on desktop (≥1024px), tablet (768px–1023px), and mobile (320px–767px) viewports. | All core screens pass a responsiveness audit with no horizontal overflow or broken layouts. |
| NFR-U03 | The system shall display clear, human-readable validation error messages that guide the user to correct invalid inputs. | Every form field with a validation error presents a contextual inline message within 500ms. |
| NFR-U04 | The system interface shall comply with WCAG 2.1 Level AA accessibility standards. | Lighthouse accessibility audit score of 90 or above on all primary user-facing pages. |

---

### 3.2 Deployability

| ID | Requirement | Measurable Criterion |
|----|-------------|----------------------|
| NFR-D01 | The system shall be deployable on any Linux-based server using Docker and Docker Compose without manual server configuration beyond environment variable setup. | A complete deployment from a clean server is achievable in under 30 minutes using the provided `docker-compose.yml`. |
| NFR-D02 | The system shall support deployment to cloud hosting platforms compatible with containerised workloads, including AWS EC2 and DigitalOcean Droplets. | The `docker-compose.yml` is verified to produce a running system on a standard Ubuntu 22.04 server. |
| NFR-D03 | All environment-specific configuration shall be managed through environment variables, with a `.env.example` template committed to the repository. | No credentials are hardcoded in the codebase. All sensitive values are loaded from environment variables. |

---

### 3.3 Maintainability

| ID | Requirement | Measurable Criterion |
|----|-------------|----------------------|
| NFR-M01 | The backend codebase shall be organised into clearly separated modules aligned with bounded domain contexts (User Management, Enrollment, Payment, Scheduling, Progress, Certificates, Notifications). | Each module resides in its own directory with its own router, service, and model files. No cross-module direct database access. |
| NFR-M02 | All REST API endpoints shall be documented in a structured API reference specifying the HTTP method, route, request body schema, authentication requirement, and example response. | An `API_REFERENCE.md` document covers 100% of implemented endpoints. |
| NFR-M03 | The system shall include documentation sufficient for a new developer to understand and run the system within 2 hours. | Onboarding documentation covers setup, architecture, module responsibilities, and common debugging steps. |
| NFR-M04 | The database schema shall be managed through versioned migration files to allow controlled, reversible schema changes. | All schema changes are applied via numbered migration files. The database can be rolled back to any previous version. |

---

### 3.4 Scalability

| ID | Requirement | Measurable Criterion |
|----|-------------|----------------------|
| NFR-S01 | The system shall support at least 100 concurrent authenticated users without response time degradation beyond the thresholds defined in NFR-P02. | Load testing with 100 concurrent virtual users shows average response time under 500ms for core API endpoints. |
| NFR-S02 | The system architecture shall support horizontal scaling by running multiple instances of the backend API behind a load balancer. | The backend is stateless; no session data is stored in memory. All session state is managed via JWT. |
| NFR-S03 | The database schema shall be extensible to accommodate additional course categories, user roles, and assessment types without requiring structural redesign. | New course categories can be added via a single `INSERT` into the `course_categories` table with no code changes required. |

---

### 3.5 Security

| ID | Requirement | Measurable Criterion |
|----|-------------|----------------------|
| NFR-SEC01 | All user passwords shall be hashed using bcrypt with a minimum cost factor of 12 before persistence. Plain-text passwords shall never be stored or logged. | A database inspection confirms no plain-text passwords exist in any user record. |
| NFR-SEC02 | All client-server communication shall be encrypted using HTTPS with a valid TLS certificate. | All HTTP requests are redirected to HTTPS. No API endpoints are accessible over unencrypted HTTP in production. |
| NFR-SEC03 | The system shall implement role-based access control (RBAC) enforced at the API layer. Students, Trainers, and Administrators shall only access routes authorised for their role. | A student JWT cannot access admin-only endpoints; HTTP 403 is returned on any such attempt. |
| NFR-SEC04 | JWT access tokens shall expire after 8 hours. Expired tokens shall be rejected with an HTTP 401 response. | Automated tests confirm that requests with an expired JWT return HTTP 401. |
| NFR-SEC05 | Uploaded proof of payment files shall be stored in a private AWS S3 bucket, accessible only via a pre-signed URL with a time-limited expiry of 15 minutes. | Direct S3 object URLs without a pre-signed token return HTTP 403. |
| NFR-SEC06 | All database inputs shall be parameterised to prevent SQL injection. No raw string interpolation shall be used in database queries. | A static code analysis scan confirms no instances of raw SQL string interpolation in the codebase. |

---

### 3.6 Performance

| ID | Requirement | Measurable Criterion |
|----|-------------|----------------------|
| NFR-P01 | All primary page views shall load within 3 seconds on a standard broadband connection (≥10 Mbps). | Lighthouse performance audit shows Time to Interactive under 3 seconds on all primary screens. |
| NFR-P02 | API responses for standard read operations (course listing, enrollment lookup, schedule retrieval) shall be returned within 500 milliseconds under normal load. | P95 response time under 500ms for these endpoints at baseline load. |
| NFR-P03 | The system shall maintain a minimum uptime of 99% during academy operating hours (Monday–Saturday, 07:00–20:00 SAST). | No more than 43 minutes of total downtime per month during operating hours. |
| NFR-P04 | Certificate PDF generation shall complete within 10 seconds per certificate. | Automated tests confirm PDF generation completes within 10 seconds for a standard certificate payload. |
| NFR-P05 | The system shall perform automated daily database backups restorable within 1 hour in the event of data loss. | A documented and tested restoration procedure confirms recovery within the 1-hour target. |

---

## 4. Requirements Traceability Matrix

The following matrix demonstrates that each functional requirement is traceable to one or more stakeholder concerns identified in [STAKEHOLDER_ANALYSIS.md](./STAKEHOLDER_ANALYSIS.md).

| Requirement ID | Requirement Summary | Primary Stakeholder(s) | Stakeholder Pain Point Addressed |
|----------------|--------------------|-----------------------|----------------------------------|
| FR01 | Student account registration | Student | No formal digital enrollment channel exists |
| FR02 | Student login | Student | No digital portal for students |
| FR03 | Course catalogue browsing | Student, Academy Owner | No central course information platform |
| FR04 | Course detail view | Student | Students must contact the academy to learn course details |
| FR05 | Course enrollment submission | Student, Administrator | Enrollment via WhatsApp is informal and error-prone |
| FR06 | Proof of payment upload | Student, Payment Staff | POP submitted via WhatsApp with no audit trail |
| FR07 | Personal schedule viewing | Student | Schedules communicated informally; students receive incorrect information |
| FR08 | Course materials access | Student | Materials shared via WhatsApp with no version control |
| FR09 | Training progress visibility | Student | No mechanism for students to monitor their own progress |
| FR10 | Certificate download | Student, Regulatory Body | Certificates issued manually; no digital version available |
| FR11 | Automated enrollment notification | Student | Enrollment confirmation is informal and inconsistent |
| FR12 | Payment status visibility | Student | Students have no visibility into payment confirmation status |
| FR13 | Trainer authentication | Trainer | Trainers have no dedicated system portal |
| FR14 | Session and course visibility | Trainer | Sessions communicated informally; no consolidated trainer view |
| FR15 | Attendance recording | Trainer, Administrator | Attendance recorded on paper registers |
| FR16 | Assessment submission | Trainer, Student | Assessment results not captured in a central system |
| FR17 | Course material upload | Trainer, Student | No formal document management for course resources |
| FR18 | Course management | Administrator, Academy Owner | Course management relies on spreadsheets |
| FR19 | Trainer profile management | Administrator | Trainer records maintained manually |
| FR20 | Enrollment approval management | Administrator | Enrollment confirmations communicated ad hoc |
| FR21 | Class schedule management | Administrator, Trainer, Student | Scheduling done in spreadsheets; conflicts are common |
| FR22 | Certificate generation and issuance | Administrator, Student, Regulatory Body | Certificates designed and printed manually per student |
| FR23 | Operational report generation | Administrator, Academy Owner | No reporting tools; data compiled manually from multiple sources |
| FR24 | Payment dashboard | Administrator, Payment Staff | No payment tracking system; POP reviewed via WhatsApp |
| FR25 | Payment confirmation and rejection | Administrator, Payment Staff | No audit trail for payment confirmations |
| FR26 | Manual cash payment recording | Administrator, Payment Staff | Cash payments recorded in a spreadsheet with no audit trail |
| FR27 | Payment confirmed email notification | Student | Students not notified promptly when payment is confirmed |

---


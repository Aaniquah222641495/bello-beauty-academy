# Activity Workflow Diagrams
## Bello Beauty Academy Platform

**Document Version:** 1.0  
**Date:** March 2026  
**Status:** Draft  

---

## Table of Contents

1. [Student Registration](#1-student-registration)
2. [Course Enrollment and Payment](#2-course-enrollment-and-payment)
3. [Admin Payment Review](#3-admin-payment-review)
4. [Trainer Records Attendance](#4-trainer-records-attendance)
5. [Certificate Generation](#5-certificate-generation)
6. [Course Creation and Publishing](#6-course-creation-and-publishing)
7. [Student Progress Tracking](#7-student-progress-tracking)
8. [Admin Report Generation](#8-admin-report-generation)

---

## 1. Student Registration

### Activity Diagram

```mermaid
flowchart TD
    Start([🟢 Start]) --> A[Student navigates to registration page]
    A --> B[Student fills in name, email, and password]
    B --> C{System validates input fields}
    C -- Invalid --> D[System shows validation error messages]
    D --> B
    C -- Valid --> E{Is the email already registered?}
    E -- Yes --> F[System shows 'Email already in use' error]
    F --> B
    E -- No --> G[System hashes password using bcrypt]
    G --> H[System creates user account with role = Student]
    H --> I[System generates email verification token]

    subgraph Parallel Actions
        I --> J[System sends verification email to student]
        I --> K[System logs account creation in audit trail]
    end

    J --> L[Student clicks verification link in email]
    L --> M{Is the verification token valid and not expired?}
    M -- Expired --> N[System shows 'Link expired' message]
    N --> O[Student requests new verification email]
    O --> I
    M -- Valid --> P[System sets account status to Active]
    P --> Q[System redirects student to login page]
    Q --> End([🔴 End])
    K --> End
```

### Explanation

This workflow shows how a new student creates an account on the platform. The email verification step ensures that only people with valid email addresses can enroll in courses, which is important because the academy needs to be able to contact students.

**Key decisions in this flow:**
- The input validation loop makes sure the student fills everything in correctly before the system even tries to create an account.
- The duplicate email check prevents two accounts being created with the same address.
- The token expiry check is there because verification emails have a 24-hour window: if a student misses it, they can request a new one instead of having to register all over again.

**Parallel actions:** Sending the verification email and writing to the audit log happen at the same time, because neither one depends on the other. This avoids unnecessary waiting.

**How this addresses stakeholder concerns:** The academy admin needs to know that only verified, real students are in the system. The email verification step directly enforces this. The audit log also means there's always a record of when accounts were created, which is useful for troubleshooting.

**Functional requirement mapping:** Covers [**FR01**](SPECIFICATION.md#61-student-requirements) (student registration), [**FR02**](SPECIFICATION.md#61-student-requirements) (login after registration), and [**NFR01**](SPECIFICATION.md#71-security) (passwords hashed with bcrypt).

---

## 2. Course Enrollment and Payment

### Activity Diagram

```mermaid
flowchart TD
    Start([🟢 Start]) --> A[Student logs in and browses the course catalogue]
    A --> B[Student clicks on a course to view details]
    B --> C{Is the course available?}
    C -- Course Full --> D[System shows 'Course Full' message]
    D --> A
    C -- Available --> E[Student clicks 'Enroll Now']
    E --> F{Is the student already enrolled in this course?}
    F -- Yes --> G[System shows 'Already Enrolled' message]
    G --> A
    F -- No --> H[System creates enrollment record\nstatus = Pending]
    H --> I[System creates payment record\nstatus = PendingUpload]

    subgraph Parallel Actions
        I --> J[System sends enrollment confirmation email to student]
        I --> K[System notifies admin of new enrollment application]
    end

    J --> L[Student navigates to the payment section]
    L --> M[Student makes payment via cash or EFT]
    M --> N[Student uploads proof of payment document]
    N --> O{File validation}
    O -- Invalid type or size --> P[System shows upload error]
    P --> N
    O -- Valid --> Q[System uploads file to AWS S3]
    Q --> R[System updates payment record with file path]
    R --> S[System notifies admin that POP is ready for review]
    S --> End([🔴 End: Awaiting Admin Confirmation])

    K --> End
```

### Explanation

This is probably the most important workflow in the whole system from the student's perspective. It covers everything from choosing a course to successfully submitting their proof of payment.

**Key decisions in this flow:**
- Checking if the course is full before letting the student apply saves them time and prevents data issues.
- The duplicate enrollment check means a student can't accidentally enroll in the same course twice.
- The file validation check (type and size) makes sure the admin only receives documents they can actually open and read.

**Parallel actions:** Once the enrollment record is created, the student gets a confirmation email and the admin gets an alert at the same time. This is important because neither action should depend on the other: the student shouldn't have to wait for the admin to be notified before getting their own confirmation.

**How this addresses stakeholder concerns:** Students need immediate feedback that their application was received. Admins need to know quickly so they can process payments without delay. Both are handled by the parallel notifications.

**Functional requirement mapping:** Covers [**FR05**](SPECIFICATION.md#61-student-requirements) (enrollment submission), [**FR10**](SPECIFICATION.md#61-student-requirements) (enrollment confirmation email), [**FR11**](SPECIFICATION.md#61-student-requirements) (payment status visibility), and [**FR12**](SPECIFICATION.md#61-student-requirements) (proof of payment upload).

---

## 3. Admin Payment Review

### Activity Diagram

```mermaid
flowchart TD
    Start([🟢 Start]) --> A[Admin logs in and opens Payment Dashboard]
    A --> B[System loads all payments with status = UnderReview]
    B --> C{Are there pending payments?}
    C -- None --> D[System shows 'No pending payments' message]
    D --> End([🔴 End])
    C -- Yes --> E[Admin selects a payment to review]
    E --> F[System retrieves the proof of payment file from AWS S3]
    F --> G[Admin views the uploaded document]
    G --> H{Is the proof of payment valid?}
    H -- Valid: correct amount and legible --> I[Admin clicks 'Confirm Payment']
    H -- Invalid: wrong amount, illegible, or unrelated --> J[Admin clicks 'Reject Payment']
    H -- Unclear --> K[Admin adds a note and defers for further review]
    K --> E

    I --> L[System updates payment status to Confirmed]
    L --> M[System updates enrollment status to Active]

    subgraph Parallel Actions
        M --> N[System sends payment confirmed email to student]
        M --> O[System logs confirmation: admin ID and timestamp]
    end

    J --> P[System updates payment status to Rejected]
    P --> Q[System sends rejection email to student with reason]
    Q --> R[System resets payment status to PendingUpload]
    R --> End

    N --> End
    O --> End
```

### Explanation

This workflow is from the admin's side. After a student uploads their proof of payment, the admin needs a clear process for reviewing it, confirming or rejecting it, and making sure the student is notified either way.

**Key decisions in this flow:**
- A three-way decision on payment validity (valid, invalid, unclear) is included because in practice not every document is obviously correct or wrong: sometimes the admin needs to look more carefully before deciding.
- The deferred review path (`Unclear → defer`) prevents the admin from making a rushed incorrect decision.

**Parallel actions:** After a payment is confirmed, the student email and the audit log entry happen at the same time. The audit log is important for financial accountability: the academy needs to know exactly which admin confirmed which payment and when.

**How this addresses stakeholder concerns:** The academy owner needs a trustworthy financial record. The parallel logging ensures every confirmation is tracked even if the email fails to send. Students need fast communication after payment: the immediate notification addresses this.

**Functional requirement mapping:** Covers [**FR24**](SPECIFICATION.md#63-administrator-requirements) (admin payment dashboard), [**FR25**](SPECIFICATION.md#63-administrator-requirements) (confirm or reject payment), [**FR26**](SPECIFICATION.md#63-administrator-requirements) (manually record cash payments), and [**FR27**](SPECIFICATION.md#63-administrator-requirements) (email notification on confirmation).

---

## 4. Trainer Records Attendance

### Activity Diagram

```mermaid
flowchart TD
    Start([🟢 Start]) --> A[Trainer logs in to trainer portal]
    A --> B[Trainer navigates to 'My Sessions']
    B --> C[System loads all sessions assigned to this trainer]
    C --> D[Trainer selects the current or recent session]
    D --> E[System loads the enrolled student list for this session]
    E --> F[Trainer begins marking attendance]
    F --> G[For each enrolled student]
    G --> H{Mark attendance status}
    H -- Present --> I[Record: Present]
    H -- Absent --> J[Record: Absent]
    H -- Late --> K[Record: Late]
    I --> L{More students?}
    J --> L
    K --> L
    L -- Yes --> G
    L -- No --> M[Trainer reviews attendance summary]
    M --> N{Is the record correct?}
    N -- No --> O[Trainer edits specific entries]
    O --> M
    N -- Yes --> P[Trainer clicks 'Submit Attendance']
    P --> Q[System saves all attendance records to database]
    Q --> R[System updates each student's progress tracker]

    subgraph Parallel Actions
        R --> S[System checks if any student's attendance\nhas dropped below 80%]
        R --> T[System marks session as Completed]
    end

    S --> U{Any student at risk?}
    U -- Yes --> V[System flags student as AtRisk in progress tracker]
    U -- No --> End([🔴 End])
    T --> End
    V --> End
```

### Explanation

This workflow shows what happens when a trainer submits attendance after a training session. It also shows how the system automatically acts on that data.

**Key decisions in this flow:**
- The three-way attendance choice (Present, Absent, Late) is important because just marking someone as present or absent isn't enough: being late affects the student's progress record differently.
- The review step before submission lets the trainer fix any mistakes before the data is saved permanently.
- The at-risk check after submission is automated: the system doesn't rely on the trainer to identify struggling students manually.

**Parallel actions:** After attendance is submitted, two things happen at the same time: the at-risk check runs on each student and the session gets marked as `Completed`. These don't need to wait for each other.

**How this addresses stakeholder concerns:** The academy needs early warnings about students who might not pass. The automated at-risk detection means management is alerted even if the trainer doesn't notice the pattern. The review step protects against accidental errors in the attendance record.

**Functional requirement mapping:** Covers [**FR15**](SPECIFICATION.md#62-trainer-requirements) (trainers record attendance), [**FR08**](SPECIFICATION.md#61-student-requirements) (students can view their attendance records), and feeds into the `AtRisk` state from my state diagram in `STATE_DIAGRAMS.md`.

---

## 5. Certificate Generation

### Activity Diagram

```mermaid
flowchart TD
    Start([🟢 Start]) --> A[Admin opens student progress dashboard]
    A --> B[Admin finds student with status = ReadyForCertification]
    B --> C[Admin clicks 'Generate Certificate']
    C --> D[System calls Progress Tracking Component to verify completion]
    D --> E{Are all completion criteria met?}
    E -- No: missing attendance or assessment --> F[System shows 'Requirements Not Met' with details]
    F --> A
    E -- Yes --> G[System calls Certificate Generation Service]
    G --> H[Service generates branded PDF:\nStudent Name, Course Name, Date, Certificate Number]
    H --> I{PDF generation successful?}
    I -- Error --> J[System logs error and notifies admin]
    J --> K[Admin retries]
    K --> G
    I -- Success --> L[System uploads PDF to AWS S3]
    L --> M[System saves certificate metadata:\nstudent ID, course ID, issue date, file path, cert number]

    subgraph Parallel Actions
        M --> N[System sends 'Certificate Ready' email to student]
        M --> O[System updates enrollment status to Completed]
    end

    N --> P[Student logs in and navigates to 'My Certificates']
    O --> P
    P --> Q[Student clicks 'Download Certificate']
    Q --> R[System retrieves PDF from AWS S3]
    R --> S[PDF is streamed to the student's browser]
    S --> End([🔴 End])
```

### Explanation

This workflow covers the full certificate process from the admin triggering it to the student downloading the PDF. The eligibility check at the beginning ensures that the system never generates a certificate for a student who has not actually finished the course.

**Key decisions in this flow:**
- The completion criteria check at the start is a hard gate: no certificate gets created unless the student genuinely qualifies.
- The PDF generation failure path is important for reliability: if the PDF service crashes or times out, the admin gets notified and can retry without having to start the whole process from scratch.

**Parallel actions:** Once the certificate metadata is saved, the student gets an email and the enrollment is updated to `Completed` at the same time. Neither depends on the other so there's no reason to wait.

**How this addresses stakeholder concerns:** The academy's reputation depends on certificates being meaningful: only graduates who actually completed the work should receive them. The eligibility gate enforces this. The parallel email means students don't have to check back: they're told immediately.

**Functional requirement mapping:** Covers [**FR22**](SPECIFICATION.md#63-administrator-requirements) (admin generates certificates) and [**FR09**](SPECIFICATION.md#61-student-requirements) (students download their PDF certificate). Connects to the Certificate state diagram in `STATE_DIAGRAMS.md`.

---

## 6. Course Creation and Publishing

### Activity Diagram

```mermaid
flowchart TD
    Start([🟢 Start]) --> A[Admin navigates to Course Management]
    A --> B[Admin clicks 'Create New Course']
    B --> C[Admin fills in:\nName, Category, Description, Duration, Prerequisites, Cost, Max Capacity]
    C --> D{Are all required fields complete?}
    D -- No --> E[System highlights missing fields]
    E --> C
    D -- Yes --> F[System saves course with status = Draft]
    F --> G[Admin optionally uploads course materials\ne.g. syllabus, resource PDFs]
    G --> H{Is the course ready to publish?}
    H -- Not yet --> I[Admin saves as Draft and exits]
    I --> End1([🔴 End: Draft Saved])
    H -- Yes --> J[Admin assigns a trainer to the course]
    J --> K{Is a qualified trainer available?}
    K -- None available --> L[Admin selects from unassigned trainer list]
    L --> J
    K -- Trainer assigned --> M[Admin sets enrollment open and close dates]
    M --> N[Admin clicks 'Publish Course']
    N --> O[System validates all publish conditions:\nTrainer assigned, dates set, description complete]
    O --> P{Validation passed?}
    P -- Fail --> Q[System shows which conditions are unmet]
    Q --> C
    P -- Pass --> R[System updates course status to Active]

    subgraph Parallel Actions on Publish
        R --> S[Course appears in student catalogue]
        R --> T[Assigned trainer receives email notification]
        R --> U[System logs course creation event]
    end

    S --> End2([🔴 End: Course Live])
    T --> End2
    U --> End2
```

### Explanation

This workflow shows how an admin creates a new course and gets it live on the platform. A draft stage is included because courses often need to be prepared over multiple sessions before they are ready to go public.

**Key decisions in this flow:**
- The required fields check at the beginning stops half-complete courses being saved in a confusing state.
- The trainer availability check is important: a course shouldn't go live without someone assigned to teach it.
- The final publish validation is a last check before the course becomes visible to students.

**Parallel actions on publish:** Three things happen at the same time when a course is published: it appears in the student catalogue, the trainer gets an email, and the event is logged. All three are independent so they can happen in parallel.

**How this addresses stakeholder concerns:** The academy can't afford to have students enrolling in courses that aren't properly set up. The multi-step validation prevents that. Trainers also need to know about new courses as soon as they're published: the parallel notification makes sure they're informed immediately.

**Functional requirement mapping:** Covers [**FR18**](SPECIFICATION.md#63-administrator-requirements) (admin creates and manages courses), [**FR19**](SPECIFICATION.md#63-administrator-requirements) (admin assigns trainers), and [**FR03**](SPECIFICATION.md#61-student-requirements) (students browse only live, active courses).

---

## 7. Student Progress Tracking

### Activity Diagram

```mermaid
flowchart TD
    Start([🟢 Start]) --> A[Trainer submits attendance after a session]
    A --> B[System updates attendance record in database]
    B --> C[System recalculates student attendance percentage]
    C --> D{Attendance >= 80%?}
    D -- Yes --> E[Progress status = OnTrack]
    D -- No --> F{Attendance < 50%?}
    F -- Yes --> G[Progress status = Failed\nEnrollment flagged for suspension]
    F -- No --> H[Progress status = AtRisk]
    H --> I[System sends at-risk alert to admin]

    E --> J[Trainer submits assessment result]
    G --> End1([🔴 End: Enrollment Suspended])

    J --> K{Assessment passed?}
    K -- Failed --> L[Result recorded as Failed\nTrainer adds feedback note]
    L --> M[Student views feedback in dashboard]
    M --> N[Trainer may schedule a reassessment]
    N --> J
    K -- Passed --> O[Result recorded as Passed]
    O --> P[System checks if all sessions AND all assessments are complete]
    P --> Q{All requirements met?}
    Q -- No --> R[Progress stays InProgress]
    R --> Start
    Q -- Yes --> S[System sets progress to ReadyForCertification]

    subgraph Parallel Actions
        S --> T[Admin notified: student is ready for a certificate]
        S --> U[System generates a completion summary]
    end

    T --> End2([🔴 End: Certificate Process Can Begin])
    U --> End2
    I --> End3([🔴 End: Admin Alerted])
```

### Explanation

This workflow shows what happens after a trainer submits attendance and an assessment: how the system processes that data and updates the student's progress accordingly. A reassessment loop is included because in a real training environment students sometimes fail an assessment the first time and are given a second chance.

**Key decisions in this flow:**
- The three-tier attendance check (>=80%, between 50–80%, below 50%) matches the three states in my student progress state diagram: `OnTrack`, `AtRisk`, and `Failed`.
- The failed assessment loop with feedback notes is realistic: trainers should be able to give students guidance before a reassessment.

**Parallel actions:** When a student reaches `ReadyForCertification`, the admin gets notified and a completion summary is generated at the same time. Both are triggered by the same event but don't depend on each other.

**How this addresses stakeholder concerns:** Trainers need to know their submission has an effect: seeing the at-risk flag update in real time gives them confidence the system is working. Academy management needs early warnings about failing students to intervene before it's too late. The automated threshold check does exactly that.

**Functional requirement mapping:** Covers [**FR08**](SPECIFICATION.md#61-student-requirements) (students view progress), [**FR15**](SPECIFICATION.md#62-trainer-requirements) (trainers record attendance), [**FR16**](SPECIFICATION.md#62-trainer-requirements) (trainers submit assessments), and sets up the precondition for [**FR22**](SPECIFICATION.md#63-administrator-requirements) (certificate generation).

---

## 8. Admin Report Generation

### Activity Diagram

```mermaid
flowchart TD
    Start([🟢 Start]) --> A[Admin navigates to Reports section]
    A --> B[Admin selects report type]
    B --> C{Which report type?}

    C -- Enrollment Report --> D[Admin sets date range and course filter]
    C -- Course Completion Report --> E[Admin selects course category filter]
    C -- Trainer Assignment Report --> F[Admin selects trainer filter]
    C -- Payment Status Report --> G[Admin selects payment status:\nPending / Confirmed / Rejected]

    D --> H[System queries enrollments within date range and filter]
    E --> I[System queries completion rates per course and category]
    F --> J[System queries sessions and students per trainer]
    G --> K[System queries payment records matching selected status]

    H --> L[System aggregates the results]
    I --> L
    J --> L
    K --> L

    L --> M{Was any data found?}
    M -- No data --> N[System shows 'No records found' message]
    N --> A
    M -- Data found --> O[System renders report as an on-screen table]
    O --> P[Admin reviews the report]
    P --> Q{Does the admin want to export?}
    Q -- No --> End1([🔴 End: Report Viewed])
    Q -- Yes --> R[Admin clicks 'Export as PDF' or 'Export as CSV']
    R --> S[System generates the export file]

    subgraph Parallel Export Actions
        S --> T[File made available for download]
        S --> U[System logs report export:\nadmin ID, report type, timestamp]
    end

    T --> End2([🔴 End: Report Exported])
    U --> End2
```

### Explanation

This workflow shows how an admin generates and optionally exports an operational report. Four different report types are included because the academy has different reporting needs: enrollment numbers, course completion, trainer workload, and payment status are all separate concerns.

**Key decisions in this flow:**
- The four-way report type split reflects these different needs: each type runs a different database query with different filters.
- The "no data found" check is important so the admin gets useful feedback rather than just an empty table with no explanation.
- The export decision is optional: not every report needs to be downloaded, sometimes the admin just needs to check something on screen.

**Parallel export actions:** When a report is exported, the file is made available for download and the export is logged at the same time. The log is there for accountability: the academy should know when reports were generated and by whom.

**How this addresses stakeholder concerns:** Academy management needs real operational data to make decisions about which courses to run, which trainers are overloaded, and whether students are progressing. Having four report types with flexible filters means management can answer specific questions without digging through the entire database. The audit log on exports also supports governance requirements.

**Functional requirement mapping:** Covers [**FR23**](SPECIFICATION.md#63-administrator-requirements) (admin generates reports on enrollments, completions, and trainer assignments) and [**FR24**](SPECIFICATION.md#63-administrator-requirements) (payment dashboard: addressed by the Payment Status Report type).
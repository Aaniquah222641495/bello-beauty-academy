# Bello Beauty Academy Platform — Agile Planning Document

**Document Version:** 1.0
**Date:** March 2026
**Assignment:** Assignment 6 — Agile User Stories, Backlog, and Sprint Planning
**Status:** Final

> **GitHub Agile Tools:**
> - 📋 [Product Backlog — GitHub Project Board](https://github.com/users/Aaniquah222641495/projects/3)
> - 🏁 [Sprint 1 Milestone](https://github.com/Aaniquah222641495/bello-beauty-academy/milestone/1)

---

## Table of Contents

1. [User Stories](#1-user-stories)
2. [Product Backlog](#2-product-backlog)
3. [Sprint Planning](#3-sprint-planning)
4. [Reflection](#4-reflection)

---

# 1. User Stories

The following user stories were derived from the functional requirements documented in [SYSTEM_REQUIREMENTS.md](./SYSTEM_REQUIREMENTS.md) and the use cases documented in [TEST_AND_USE_CASE.md](./TEST_AND_USE_CASE.md). Each story follows the standard format: "As a [role], I want [action] so that [benefit]." All stories adhere to the INVEST criteria, ensuring each one is Independent, Negotiable, Valuable, Estimable, Small, and Testable. Stories are organised into two groups based on their source.

## 1.1 Stories from Functional Requirements (8+)

The stories below were derived from the functional requirement statements in Assignment 4. Each story focuses on a specific capability the system must deliver to a named user role.

| Story ID | User Story | Acceptance Criteria | Priority | FR | UC |
|----------|------------|--------------------|---------:|:--:|:--:|
| US001 | As a **student**, I want to register for an account so that I can access the platform and enroll in courses. | Account is created with role `student`. Password is stored as a bcrypt hash. Confirmation email is sent within 60 seconds. Duplicate emails are rejected with a clear message. | High | FR01 | UC01 |
| US002 | As a **student**, I want to browse the course catalogue by category so that I can find the right course for me. | All active courses are listed. Courses are filterable by category (Lash, Brow, Nail, Makeup). Each listing shows course name, duration, and cost. | High | FR03 | UC03 |
| US003 | As a **student**, I want to enroll in a course so that I can begin my beauty training. | Enrollment record created with status `pending`. Payment record created with status `pending`. Confirmation email sent within 60 seconds. Duplicate enrollment is rejected with a clear message. | High | FR05 | UC05 |
| US004 | As a **student**, I want to upload my proof of payment so that the academy can verify my payment and activate my enrollment. | Accepted formats: JPEG, PNG, PDF under 5 MB. File stored in S3. Confirmation message shown. Administrator notified automatically. | High | FR06 | UC06 |
| US005 | As a **student**, I want to view my payment status so that I know whether my enrollment has been confirmed or if I need to resubmit. | Payment status (Pending, Confirmed, Rejected) is visible on the enrollment dashboard. Rejected payments show a resubmission prompt. | High | FR12 | UC07 |
| US009 | As a **trainer**, I want to view my assigned sessions so that I know when and where I need to deliver training. | All assigned sessions visible on login. Sessions show date, time, venue, and enrolled student count. Only sessions assigned to the logged-in trainer are shown. | High | FR14 | UC12 |
| US010 | As a **trainer**, I want to record student attendance so that there is a digital record of who attended each session. | Attendance statuses available: Present, Absent, Late. Recording window open for 24 hours after session end. Records saved with trainer ID and timestamp. Student dashboards updated immediately. | High | FR15 | UC13 |
| US011 | As a **trainer**, I want to submit assessment results so that students can track their competency progress and certificates can be issued. | Assessment requires a competency rating and optional comment. Results immediately visible on student progress dashboard. Records include trainer ID and timestamp. | High | FR16 | UC14 |
| US012 | As an **administrator**, I want to manage the course catalogue so that the academy's training offerings are always accurate and up to date. | Admin can create, edit, and deactivate courses. Deactivated courses removed from public catalogue but retained in admin dashboard. Courses with active enrollments cannot be deleted. | High | FR18 | UC16 |
| US013 | As an **administrator**, I want to manage student enrollments so that I can approve, reject, or update enrollment applications. | All enrollments listed with student name, course, date, and status. Status changes trigger automated email to student. | High | FR20 | UC18 |
| US020 | As a **system administrator**, I want all user passwords encrypted using bcrypt so that user credentials are protected against data breaches. | No plain-text passwords stored in the database. bcrypt cost factor of minimum 12 applied. Verified by database inspection. | High | NFR-SEC01 | N/A |

---

## 1.2 Stories from Use Cases (4+)

The stories below were derived from the use case models developed in Assignment 5. Each story focuses on the interaction flow between an actor and the system rather than the underlying system capability alone.

| Story ID | User Story | Acceptance Criteria | Priority | FR | UC |
|----------|------------|--------------------|---------:|:--:|:--:|
| US006 | As a **student**, I want to view my class schedule so that I can plan my attendance around my personal commitments. | Schedule only accessible once enrollment is active. Sessions shown in chronological order with date, time, venue, and trainer name. Sessions within the next 7 days are visually highlighted. | Medium | FR07 | UC08 |
| US007 | As a **student**, I want to track my training progress so that I can see my attendance and assessment results for each course. | Attendance status (Present, Absent, Late) shown per session. Assessment results shown where submitted. Dashboard updated within 1 hour of trainer submission. | Medium | FR09 | UC10 |
| US008 | As a **student**, I want to download my certificate so that I have proof of my qualification to show clients and employers. | Download button only appears once certificate has been issued. PDF contains student name, course name, completion date, and unique certificate number. Download link persists for the lifetime of the account. | Medium | FR10 | UC11 |
| US014 | As an **administrator**, I want to confirm or reject student proof of payment so that enrollments are only activated for verified payments. | Confirming sets payment to `confirmed` and enrollment to `active`. Rejecting sets payment to `rejected`. Email dispatched to student in both cases. Action logged with admin ID and timestamp. | High | FR25 | UC21 |
| US015 | As an **administrator**, I want to generate certificates for students who have completed a course so that they receive formal recognition of their qualification. | Certificate can only be generated once all assessments are submitted. PDF contains student name, course name, completion date, and unique certificate number. Enrollment updated to `completed`. Email sent to student. | Medium | FR22 | UC23 |

---

# 2. Product Backlog

The product backlog below compiles all user stories and prioritises them using MoSCoW categorisation (Must-have, Should-have, Could-have, Won't-have). Effort estimates use Fibonacci story points where 1 represents a trivial task and 8 represents a very complex one.

**Prioritisation Justification**

Must-have stories form the minimum viable product. These are stories without which the platform cannot function at even a basic level. Students cannot use the platform without registration, enrollment, and payment verification. Trainers cannot perform their role without session visibility and attendance recording. Administrators cannot operate the academy without enrollment management and the payment confirmation workflow.

Should-have stories add significant value to the user experience but the system can operate without them in the first sprint. Could-have stories are desirable enhancements that will be planned into later sprints once the core system is stable. No stories have been classified as Won't-have for this release, as all identified stories are traceable to confirmed stakeholder requirements.

| Story ID | User Story | MoSCoW | Effort Estimate (Story Points) | Dependencies |
|----------|------------|--------|:---:|--------------|
| US001 | Student registers for an account | Must-have | 2 | None |
| US020 | Passwords encrypted with bcrypt | Must-have | 1 | US001 |
| US002 | Student browses the course catalogue | Must-have | 2 | US001 |
| US003 | Student enrolls in a course | Must-have | 3 | US001, US002 |
| US004 | Student uploads proof of payment | Must-have | 3 | US003 |
| US005 | Student views payment status | Must-have | 2 | US004 |
| US009 | Trainer views assigned sessions | Must-have | 2 | None |
| US010 | Trainer records student attendance | Must-have | 3 | US009 |
| US011 | Trainer submits assessment results | Must-have | 3 | US010 |
| US012 | Admin manages course catalogue | Must-have | 3 | None |
| US013 | Admin manages student enrollments | Must-have | 3 | US003 |
| US014 | Admin confirms or rejects payment | Must-have | 3 | US004 |
| US006 | Student views class schedule | Should-have | 2 | US014 |
| US007 | Student tracks training progress | Should-have | 2 | US011 |
| US015 | Admin generates certificates | Should-have | 5 | US011, US014 |
| US008 | Student downloads certificate | Could-have | 3 | US015 |

---

# 3. Sprint Planning

## Sprint Goal

**Sprint 1 Goal:** Deliver the core enrollment and payment verification flow so that a student can register, browse courses, enroll, upload proof of payment, and have that payment confirmed or rejected by an administrator. This forms the foundational MVP of the Bello Beauty Academy Platform.

**Sprint Duration:** 2 weeks
**Sprint Capacity:** 40 hours

This sprint directly addresses the most critical stakeholder pain points identified in Assignment 4. Specifically, it resolves the absence of a structured digital enrollment process and the lack of a systematic payment tracking system. Delivering this flow end-to-end provides the platform with its first complete, testable slice of value.

---

## Selected User Stories for Sprint 1

| Story ID | User Story | Story Points | Justification |
|----------|------------|:------------:|---------------|
| US001 | Student registers for an account | 2 | Entry point for all student interactions. Nothing else works without this. |
| US020 | Passwords encrypted with bcrypt | 1 | Non-negotiable security requirement. Must ship with US001. |
| US002 | Student browses the course catalogue | 2 | Students need to see courses before they can enroll. |
| US003 | Student enrolls in a course | 3 | Core business process. The primary purpose of the platform. |
| US004 | Student uploads proof of payment | 3 | Directly follows enrollment. Enables the payment verification flow. |
| US014 | Admin confirms or rejects payment | 3 | Completes the enrollment lifecycle. Without this, no student can become active. |

**Total Story Points: 14**

---

## Sprint Backlog

| Task ID | Task Description | Story ID | Assigned To | Estimated Hours | Status |
|---------|-----------------|----------|-------------|:--------------:|--------|
| T-001 | Create `POST /api/auth/register` endpoint with bcrypt password hashing | US001, US020 | Dev Team | 3 | To Do |
| T-002 | Build student registration form UI with validation | US001 | Dev Team | 3 | To Do |
| T-003 | Dispatch confirmation email on successful registration | US001 | Dev Team | 2 | To Do |
| T-004 | Write unit tests for registration endpoint | US001 | Dev Team | 2 | To Do |
| T-005 | Create `GET /api/courses` endpoint returning active courses filterable by category | US002 | Dev Team | 3 | To Do |
| T-006 | Build course catalogue UI with category filter | US002 | Dev Team | 3 | To Do |
| T-007 | Build course detail page UI | US002 | Dev Team | 2 | To Do |
| T-008 | Create `POST /api/enrollments` endpoint creating enrollment and payment records | US003 | Dev Team | 3 | To Do |
| T-009 | Build Enroll button and confirmation UI on course detail page | US003 | Dev Team | 2 | To Do |
| T-010 | Dispatch enrollment confirmation email with payment instructions | US003 | Dev Team | 2 | To Do |
| T-011 | Create `POST /api/payments/:enrollmentId/upload` endpoint with S3 file storage | US004 | Dev Team | 4 | To Do |
| T-012 | Build proof of payment upload form UI with file type and size validation | US004 | Dev Team | 3 | To Do |
| T-013 | Dispatch admin notification on POP upload | US004 | Dev Team | 1 | To Do |
| T-014 | Create `GET /api/payments` payment dashboard endpoint for admin | US014 | Dev Team | 2 | To Do |
| T-015 | Build admin payment dashboard UI with POP viewer | US014 | Dev Team | 3 | To Do |
| T-016 | Create `POST /api/payments/:id/confirm` and `POST /api/payments/:id/reject` endpoints | US014 | Dev Team | 3 | To Do |
| T-017 | Dispatch confirmation or rejection email to student on payment decision | US014 | Dev Team | 2 | To Do |
| T-018 | Write integration tests for the full enrollment and payment flow | US003, US004, US014 | Dev Team | 4 | To Do |

**Total Estimated Hours: 47 hours**

> **Capacity Note:** The estimated hours marginally exceed the 40-hour sprint capacity. Task T-018 (integration tests) may be deferred to Sprint 2 if required, reducing the sprint total to 43 hours. Alternatively, UI tasks may be parallelised to remain within capacity.

---

# 4. Reflection

## Challenges in Prioritisation, Estimation, and Aligning Agile with Stakeholder Needs

Going into this assignment I thought the hardest part would be writing the user stories. It was not. The hardest part was deciding what to leave out of the first sprint.

The system has 20 user stories and there is a genuine temptation to want to build everything at once, especially when you are the only person working on it and you can see the whole picture in your head. The Agile approach pushes back against that instinct. A sprint is two weeks. That is a fixed boundary and nothing about wanting to finish everything faster changes it. Working out what genuinely belongs in Sprint 1 and what can wait forced me to think more carefully about what the system actually needs to function at a basic level versus what makes it better.

The enrollment and payment flow was the obvious choice for Sprint 1 because without it, nothing else in the system has any purpose. A student cannot access a schedule, materials, or a certificate without first enrolling and having their payment confirmed. An administrator cannot confirm a payment that has not been submitted. The flow is sequential and every other piece of the system depends on it. Recognising that dependency chain made the prioritisation feel logical rather than arbitrary.

Estimation was a different kind of challenge. Story points are supposed to reflect complexity and uncertainty, not hours. But when you are working alone and you are also the person who designed the system, it is very easy to estimate based on how long you personally expect each task to take rather than how complex it genuinely is. I had to remind myself several times that story points are relative, not absolute. Registering a user is a 2 because it is simpler than enrolling in a course, which is a 3 because it creates multiple records and triggers an email. The certificate generation is a 5 because it involves PDF generation, S3 storage, and email dispatch all at once. That framing helped more than trying to convert everything into hours first.

The MoSCoW prioritisation also revealed something uncomfortable. Several things I had thought of as essential turned out to be Should-have or Could-have when I applied the test honestly. Certificate download felt important to me because it is the end goal of the whole system, but the system can function without it in Sprint 1. A student can enroll, pay, train, and be assessed without ever downloading a certificate in the first two weeks. Accepting that was harder than I expected.

The biggest internal resistance was not about technical decisions at all. It was about the feeling that an incomplete system is not a real product. Agile requires you to be comfortable with delivering something small and functional rather than something large and finished. The MVP mindset means accepting that Sprint 1 will not impress anyone who sees the full specification, but it will work, it will be testable, and it will prove that the core of the system does what it is supposed to do. That is worth more than a finished looking system that has not been tested end to end.

---


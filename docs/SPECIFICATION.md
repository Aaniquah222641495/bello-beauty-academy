# Software Requirements Specification :Bello Beauty Academy Platform

**Document Version:** 2.0
**Date:** March 2026
**Status:** Updated — Assignment 4 Revision

---

## Table of Contents

1. [Project Title](#1-project-title)
2. [Domain Description](#2-domain-description)
3. [Problem Statement](#3-problem-statement)
4. [System Scope](#4-system-scope)
5. [System Users and Actors](#5-system-users-and-actors)
6. [System Requirements](#6-system-requirements)
7. [Training Courses Offered](#7-training-courses-offered)
8. [Future Scope](#8-future-scope)

---

## 1. Project Title

**Bello Beauty Academy Platform**

*A professional web-based management system for the Bello Beauty Academy, offering certified training courses in lash artistry, brow techniques, nail technology, and makeup artistry.*

---

## 2. Domain Description

### 2.1 The Beauty Education and Professional Training Industry

The professional beauty industry is one of the fastest-growing vocational sectors globally. It encompasses a wide range of disciplines including cosmetology, esthetics, nail technology, lash artistry, brow artistry, and makeup artistry. As consumer demand for professional beauty services grows, so does the need for formally trained, certified beauty practitioners.

**Beauty academies** occupy a critical role in this ecosystem. Unlike beauty salons or spas that deliver services to end customers, beauty academies are **educational institutions** focused exclusively on training aspiring beauty professionals. Students enroll in structured programs, learn techniques under the supervision of experienced trainers, and complete assessments to earn recognised industry certifications.

### 2.2 Professional Beauty Certifications

Certifications in the beauty industry serve as formal proof of competency. They allow graduates to operate professionally, meet industry and insurance requirements, and build client trust. Certification programs typically cover:

- **Lash Extensions** — classic, volume, hybrid, and mega volume application techniques
- **Brow Artistry** — shaping, tinting, and halaal-compliant brow techniques
- **Nail Technology** — gel nails, acrylic nails, and professional nail care
- **Makeup Artistry** — professional, bridal, mature skin, and advanced glam techniques

Each certification course involves a structured curriculum, hands-on practical training, assessments, and a final award of a certificate on successful completion.

### 2.3 Challenges of Manual Training Management

Many beauty academies, particularly small-to-medium institutions, continue to manage their operations using manual or semi-digital methods such as paper registers, spreadsheets, WhatsApp messages, and physical filing systems. This approach introduces numerous operational inefficiencies:

- **Student Enrollment** — manually recording student details and course selections is time-consuming and error prone
- **Class Scheduling** — coordinating trainer availability and room scheduling manually leads to conflicts and miscommunication
- **Progress Tracking** — monitoring each student's attendance and competency assessments across multiple courses is difficult to maintain without a centralised system
- **Certification Records** — generating, issuing, and storing certificates manually is slow, inconsistent, and difficult to verify
- **Communication** — notifying students and trainers of schedule changes or new sessions relies on informal channels

### 2.4 The Importance of Digital Training Management Platforms

A purpose-built digital academy management platform addresses these challenges by centralising all academy operations into a single, accessible system. It provides:

- A single source of truth for all course, student, and trainer data
- Automated enrollment and scheduling workflows
- Real-time progress tracking dashboards
- Digital certificate generation and verification
- Automated notifications to students and trainers
- Reporting tools for administrative decision-making

---

## 3. Problem Statement

The Bello Beauty Academy currently manages its training operations through a combination of manual processes, spreadsheets, and informal communication channels. This approach is insufficient for a growing academy and results in the following problems:

1. **Inefficient Student Enrollment** — There is no structured digital enrollment process. Students enroll via phone, WhatsApp, or in person visits, leading to duplicate records, missed applications, and administrative bottlenecks.

2. **Uncoordinated Class Scheduling** — Class timetables are managed in spreadsheets and shared informally. Scheduling conflicts between trainers and sessions occur regularly, and students frequently receive incorrect or late schedule information.

3. **Incomplete Progress Records** — Student attendance and practical assessments are recorded manually on paper. There is no centralised system to monitor which students are progressing, falling behind, or ready to receive their certificates.

4. **Manual Certificate Issuance** — Certificates are designed and printed manually for each student. This process is slow, inconsistent in format, and provides no digital verification mechanism.

5. **No Centralised Course Information** — Prospective students have no digital portal through which to browse available courses, view course details, or compare training options.

6. **Lack of Operational Visibility** — Academy management has no dashboard or reporting system to monitor enrollment numbers, course capacity, trainer workload, or revenue trends.

7. **No Payment Tracking System** — Proof of payment documents arrive informally via WhatsApp with no organised queue, audit trail, or systematic confirmation workflow.

**The Bello Beauty Academy Platform** is proposed as a web-based management system that will resolve each of these challenges by digitising and automating the core operations of the academy.

---

## 4. System Scope

This section defines the boundaries of the system for the initial release. The scope has been designed to be comprehensive yet realistic.

### 4.1 In Scope

#### Student Features
| Feature | Description |
|---------|-------------|
| Course Browsing | Students can view all available courses, categories, and course details |
| Student Registration | Students can create a personal account on the platform |
| Course Enrollment | Students can enroll in one or more training courses |
| Schedule Viewing | Students can view their personal class timetable |
| Course Materials | Students can access uploaded course materials (PDFs, documents) |
| Progress Tracking | Students can view their attendance record and assessment results |
| Certificate Download | Students can download their certificate upon course completion |

#### Trainer Features
| Feature | Description |
|---------|-------------|
| Session Management | Trainers can view and manage their assigned training sessions |
| Student Attendance | Trainers can record student attendance per session |
| Progress Recording | Trainers can submit student assessment and progress records |
| Course Materials | Trainers can upload course materials for their assigned courses |

#### Administrator Features
| Feature | Description |
|---------|-------------|
| Course Management | Admin can create, edit, and deactivate training courses |
| Trainer Management | Admin can add and manage trainer profiles |
| Enrollment Management | Admin can view, approve, and manage student enrollments |
| Schedule Management | Admin can create and manage class schedules and sessions |
| Certificate Management | Admin can generate and issue digital certificates |
| Report Generation | Admin can generate reports on enrollments, progress, and courses |

### 4.2 Out of Scope

The following features are explicitly excluded from this version of the system:

- Online payment processing or fee collection
- Live video streaming or virtual classes
- Mobile native applications (iOS/Android)
- Integration with third-party learning management systems (LMS)
- Public-facing marketing website

---

## 5. System Users and Actors

The Bello Beauty Academy serves three primary user roles. Each role has clearly defined responsibilities and access permissions within the system.

### 5.1 Student

A **Student** is a registered individual who has enrolled or intends to enroll in one or more beauty training certification courses at the academy.

**Interactions with the system:**
- Creates and manages a personal account
- Browses and searches the course catalogue
- Submits enrollment applications for courses
- Views their personal class schedule and timetable
- Accesses course materials and resources
- Monitors their own training progress and attendance
- Downloads their digital certificate upon successful course completion
- Receives automated email notifications about schedule updates and enrollment confirmations

### 5.2 Trainer

A **Trainer** is a qualified beauty professional employed by the academy to deliver training sessions in their area of specialisation.

**Interactions with the system:**
- Logs in to a trainer-specific portal
- Views their assigned courses and upcoming sessions
- Records student attendance for each session
- Submits student progress and competency assessment results
- Uploads course materials, resources, and session notes
- Communicates session-specific updates through the platform

### 5.3 Administrator

An **Administrator** is a member of academy staff responsible for the overall operational management of the platform and the academy.

**Interactions with the system:**
- Manages the full course catalogue — creates, edits, and archives courses
- Adds, updates, and deactivates trainer profiles
- Reviews and processes student enrollment requests
- Assigns trainers to courses and sessions
- Creates and publishes class schedules
- Initiates certificate generation for students who have completed courses
- Generates operational reports for management review
- Manages system users and access permissions

---

## 6. System Requirements

The full functional and non-functional requirements for the Bello Beauty Academy Platform are documented in [SYSTEM_REQUIREMENTS.md](./SYSTEM_REQUIREMENTS.md). That document includes all 27 functional requirements with acceptance criteria, a full traceability matrix linking each requirement to its stakeholder, and non-functional requirements across six quality attribute categories: Usability, Deployability, Maintainability, Scalability, Security, and Performance.

---

## 7. Training Courses Offered

The academy offers certification courses across four professional categories.

### 8.1 Lash Courses

| Course | Description |
|--------|-------------|
| Classic Lash Extensions Course | Foundational lash extension techniques using classic individual lash application |
| Volume Lash Extensions Course | Advanced volume fan creation for fuller lash sets |
| Hybrid Lash Extensions Course | Combined classic and volume techniques for versatile styling |
| Mega Volume Lash Extensions Course | Expert-level multi-lash fan application for dramatic results |
| Complete Lash Mastery Course | Comprehensive program covering classic, volume, hybrid, and mega volume techniques |

### 8.2 Brow Courses

| Course | Description |
|--------|-------------|
| Brow Shaping Course | Professional techniques for shaping and mapping client brows |
| Brow Tint Course | Safe and effective eyebrow tinting application |
| Halaal Brows Course | Brow techniques compliant with Halaal beauty standards |
| Complete Brow Mastery Course | Full program covering shaping, tinting, and Halaal brow techniques |

### 8.3 Nail Courses

| Course | Description |
|--------|-------------|
| Nail Technician Certification Course | Professional nail care and client preparation fundamentals |
| Gel Nail Application Course | Application and removal techniques for gel nail systems |
| Acrylic Nail Application Course | Acrylic nail sculpting, application, and finishing techniques |
| Complete Nail Technician Master Course | Comprehensive program covering gel, acrylic, and professional nail techniques |

### 8.4 Makeup Courses

| Course | Description |
|--------|-------------|
| Professional Makeup Artistry Course | Core professional makeup techniques for various occasions |
| Bridal Makeup Course | Specialist makeup for bridal clients including trials and day-of application |
| Makeup for Mature Skin Course | Adapted techniques for application on mature skin types |
| Advanced Glam Makeup Course | High-fashion and editorial glam makeup techniques |
| Complete Makeup Mastery Course | All-inclusive program covering professional, bridal, mature skin, and advanced glam |

---

## 8. Future Scope

The following features are out of scope for the current version of the system but have been identified and designed for future implementation.

### 9.1 Online Card Payment — PayFast Integration

The current system supports cash and EFT payments confirmed manually by an administrator. A planned future enhancement is online card payment via **PayFast**, the leading South African payment gateway. This would allow students to pay by card instantly at enrollment with no administrator intervention required.

The planned flow works as follows: the student selects online payment, the backend generates a signed PayFast payment request and redirects the student to the PayFast hosted payment page, and on successful payment PayFast sends an Instant Transaction Notification (ITN) webhook to the platform. The system validates the webhook signature and amount, then automatically activates the enrollment.

This feature is deferred from the current version because it requires a registered PayFast merchant account and a live publicly accessible server for ITN webhook delivery. It is planned for implementation in the next release.

**Planned future requirements:**

| ID | Requirement |
|----|-------------|
| FR-F1 | The system shall allow students to select online card payment as an alternative to cash/EFT at enrollment. |
| FR-F2 | The system shall generate a signed PayFast payment request and redirect the student to the PayFast hosted payment page. |
| FR-F3 | The system shall expose a secure ITN webhook endpoint to receive payment notifications from PayFast. |
| FR-F4 | The system shall validate all incoming ITN webhooks by verifying the MD5 signature, payment amount, and status before activating any enrollment. |
| FR-F5 | The system shall automatically activate enrollment upon successful ITN validation, with no administrator action required. |

### 9.2 Native Mobile Application

A React Native mobile application for iOS and Android to allow students to access schedules, course materials, and certificates from their mobile devices. The existing REST API is already designed to support mobile clients.

### 9.3 Live Virtual Classes

Integration with a video conferencing API (Zoom or Google Meet) to support remote training sessions alongside in-person classes.

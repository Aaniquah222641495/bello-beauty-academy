# STAKEHOLDER_ANALYSIS.md — Bello Beauty Academy Platform

**Document Version:** 1.0
**Date:** March 2026
**Assignment:** Assignment 4 — Stakeholder Analysis
**Status:** Final

---

## Table of Contents

1. [Overview](#1-overview)
2. [Stakeholder Identification](#2-stakeholder-identification)
3. [Stakeholder Analysis Table](#3-stakeholder-analysis-table)
4. [Detailed Stakeholder Profiles](#4-detailed-stakeholder-profiles)

---

## 1. Overview

Stakeholder analysis is a foundational activity in requirements engineering. It ensures that the system being designed accounts for the needs, concerns, and constraints of all parties who will interact with or be affected by the platform. Failing to identify stakeholders early leads to incomplete requirements, scope creep, and a system that does not adequately serve its users.

This document identifies and analyses all key stakeholders for the **Bello Beauty Academy Platform**. For each stakeholder, their role, key concerns, current pain points, and measurable success metrics are documented. These insights directly inform the functional and non-functional requirements captured in [SYSTEM_REQUIREMENTS.md](./SYSTEM_REQUIREMENTS.md).

---

## 2. Stakeholder Identification

Seven stakeholders have been identified for the Bello Beauty Academy Platform, spanning direct users of the system and parties with indirect but significant interests.

| # | Stakeholder | Type | Involvement |
|---|-------------|------|-------------|
| 1 | Student | Direct User | Primary consumer of course, schedule, and certificate features |
| 2 | Trainer | Direct User | Delivers training and records student progress |
| 3 | Academy Administrator | Direct User | Manages all operational aspects of the academy |
| 4 | Academy Owner / Principal | Indirect Stakeholder | Oversees academy performance and financial health |
| 5 | IT Support / System Developer | Indirect Stakeholder | Responsible for deployment, maintenance, and system integrity |
| 6 | Payment Processing Staff | Indirect Stakeholder | Verifies cash and EFT payments; reviews proof of payment documents |
| 7 | Regulatory / Certification Body | Indirect Stakeholder | Sets standards for beauty certifications; validates course credibility |

---

## 3. Stakeholder Analysis Table

| Stakeholder | Role | Key Concerns | Pain Points | Success Metrics |
|-------------|------|--------------|-------------|-----------------|
| **Student** | • Enrolls in beauty training courses <br> • Tracks their own learning progress <br> • Downloads their certification on completion | • Easy course discovery and clear enrollment process <br> • Timely schedule access after enrollment <br> • Reliable certificate download | • No central platform to browse courses <br> • Enrollment via WhatsApp is informal and unreliable <br> • No visibility into training progress <br> • Certificates issued slowly and inconsistently | • Enrollment completed in under 5 minutes <br> • Schedule accessible within 24 hours of enrollment confirmation <br> • Certificate available for download within 48 hours of course completion |
| **Trainer** | • Delivers training sessions <br> • Records student attendance and competency assessments <br> • Uploads course materials | • Clear visibility of assigned sessions <br> • Simple attendance recording interface <br> • Ability to upload resources easily | • Session assignments communicated informally via WhatsApp <br> • Attendance recorded on paper registers <br> • No digital system to track student progress across sessions | • All assigned sessions visible on login <br> • Attendance recorded digitally for 100% of sessions <br> • Course materials uploaded without technical difficulty |
| **Academy Administrator** | • Manages courses, trainers, enrollments, schedules, payments, and certificates <br> • Reviews and confirms proof of payment submissions <br> • Generates operational reports | • Centralised control of all academy operations <br> • Real-time enrollment and payment visibility <br> • Reduced manual administrative workload | • Hours spent managing spreadsheets for enrollments and scheduling <br> • No payment tracking system exists <br> • Certificate generation is manual and time-consuming | • Administrative processing time reduced by at least 50% <br> • All payment records visible on a single dashboard <br> • Certificates generated in under 2 minutes |
| **Academy Owner / Principal** | • Sets strategic direction <br> • Monitors academy growth, revenue, and operational performance <br> • Not a direct system user but has significant interest in reliability | • High-level reporting on enrollment numbers, completion rates, and revenue <br> • Assurance that the system is reliable and scalable <br> • Data-driven decision-making capability | • No reporting tools available <br> • Operational visibility relies entirely on manual updates from staff <br> • Growth in student numbers increases manual workload disproportionately | • Monthly enrollment and revenue reports available on demand <br> • Course completion rate trackable at any time <br> • No system downtime during operating hours |
| **IT Support / System Developer** | • Deploys, maintains, and updates the platform <br> • Ensures system security and availability <br> • Manages documentation and backups | • Clean, maintainable codebase <br> • Clear documentation and containerised deployment <br> • Automated backups and predictable system behaviour | • Systems often deployed without documentation or deployment guides <br> • No automated backup strategy currently in place <br> • Manual deployment processes introduce inconsistency | • System deployable via Docker Compose in under 30 minutes <br> • API documentation available for all endpoints <br> • Automated daily database backups confirmed |
| **Payment Processing Staff** | • Reviews uploaded proof of payment documents <br> • Confirms or rejects cash and EFT payments <br> • Maintains accurate payment records with an audit trail | • Clear queue of payments awaiting review <br> • Easy access to uploaded POP documents <br> • Simple confirm or reject workflow | • POP documents received via WhatsApp and stored inconsistently <br> • No audit trail for confirmed payments <br> • Enrollment activation requires manual follow-up communication | • All pending payments visible in a single dashboard view <br> • POP documents accessible with one click <br> • Every confirmation logged with timestamp and staff identity |
| **Regulatory / Certification Body** | • Sets professional standards and recognition criteria for beauty certifications <br> • Does not interact directly with the platform <br> • Validates that certificates meet industry competency standards | • Certificates must be traceable, verifiable, and consistently formatted <br> • Alignment with industry competency standards <br> • Academy must produce verifiable records on request | • No digital verification mechanism for certificates currently issued <br> • No standardised certificate format exists <br> • Academy cannot confirm certificate authenticity if challenged | • Every certificate contains a unique identifier <br> • Certificate metadata stored and retrievable from the database <br> • Consistent formatting across all issued certificates |

---

## 4. Detailed Stakeholder Profiles

### 4.1 Student

**Role:** The student is the primary user of the platform. They interact with the system to browse available certification courses, create an account, enroll in a course, submit proof of payment, view their class schedule, access course materials, monitor their training progress, and download their certificate on course completion.

**Key Concerns:**
- Ability to independently browse and understand the course catalogue without needing to contact the academy
- A clear and straightforward enrollment process with transparent confirmation
- Timely access to class schedules so they can plan their personal commitments
- Confidence that their training progress is being recorded accurately
- A reliable and professional digital certificate they can present to clients or employers

**Pain Points:**
- Currently, students must contact the academy via phone or WhatsApp to enquire about courses, which is inefficient and error-prone
- Enrollment confirmation is informal and inconsistent
- Students have no visibility into their own attendance or assessment records
- Certificates take days or weeks to receive and are not available digitally

**Success Metrics:**
- Course enrollment completed in under 5 minutes from account creation
- Schedule accessible within 24 hours of payment confirmation
- Progress dashboard visible and up-to-date after every session
- Certificate downloadable in PDF format within 48 hours of course completion

---

### 4.2 Trainer

**Role:** The trainer is a qualified beauty professional responsible for delivering scheduled training sessions. They interact with the system to view their assigned sessions, take student attendance digitally, record competency assessment results, and upload course materials.

**Key Concerns:**
- A clear and consolidated view of all their assigned sessions and student rosters
- A simple attendance-recording interface that does not require technical expertise
- The ability to upload course materials for students to access between sessions
- Confidence that assessment records they submit are stored accurately

**Pain Points:**
- Session assignments are currently communicated informally via WhatsApp or in-person
- Attendance is taken on paper registers that are later transcribed manually
- Course materials are shared via WhatsApp or USB drives with no version control
- There is no system through which trainers can see a student's cumulative progress

**Success Metrics:**
- All assigned sessions visible immediately upon login
- Attendance recorded digitally for every session, eliminating paper registers
- Course materials uploaded in under 3 minutes without technical assistance
- Assessment results saved and reflected on student progress dashboards in real time

---

### 4.3 Academy Administrator

**Role:** The administrator manages the full operational scope of the academy. They create and maintain the course catalogue, manage trainer profiles, approve or reject student enrollment applications, create and publish class schedules, confirm or reject student payment records, and generate operational reports.

**Key Concerns:**
- A single, centralised platform from which all academy operations can be managed
- Real-time visibility into payment status across all enrollments
- Efficient tools for generating and issuing certificates
- Comprehensive reporting to support operational decision-making

**Pain Points:**
- Current operations rely entirely on spreadsheets, paper documents, and informal channels
- Payment verification requires manually checking WhatsApp messages for POP documents
- Certificate generation involves designing and printing each certificate individually
- There is no reporting mechanism; data must be aggregated manually from multiple sources

**Success Metrics:**
- All active enrollments and payment statuses visible on a single dashboard
- Administrative workload reduced by at least 50% compared to current manual processes
- Certificates generated and stored digitally in under 2 minutes per student
- Monthly reports exportable in under 1 minute

---

### 4.4 Academy Owner / Principal

**Role:** The academy owner holds strategic responsibility for the institution. They are not a direct system user but have a significant interest in the platform's ability to support academy growth, operational efficiency, and reliable revenue tracking.

**Key Concerns:**
- High-level visibility into enrollment volumes, course completion rates, and revenue
- Confidence that the system is secure and reliable enough to be trusted with sensitive student and financial data
- Scalability of the platform to support growth without requiring complete re-engineering

**Pain Points:**
- Currently, all operational data must be manually compiled for review
- There is no real-time view of how many students are enrolled, how many have paid, or which courses are most popular
- Growth in student numbers increases the manual workload disproportionately

**Success Metrics:**
- Enrollment and revenue reports available on demand without manual compilation
- System supports at least 100 concurrent users without performance degradation
- Platform architecture is extensible to support future features such as online payments and mobile access

---

### 4.5 IT Support / System Developer

**Role:** The IT support stakeholder is responsible for deploying, maintaining, and updating the platform. They have a strong interest in clean architecture, comprehensive documentation, and reproducible deployment environments.

**Key Concerns:**
- A well-structured, modular codebase that is maintainable by a single developer
- Clear API documentation and database schema documentation
- Containerised deployment so the system can be run consistently across environments
- Automated backups and monitoring to prevent data loss

**Pain Points:**
- Systems without clear documentation are difficult and time-consuming to maintain
- Manual deployment processes introduce inconsistency across environments
- Lack of automated backups creates a risk of unrecoverable data loss

**Success Metrics:**
- Full system deployable via `docker-compose up` in under 30 minutes
- API documentation covers all endpoints with request/response examples
- Automated daily database backups verified and restorable
- System logs errors in a structured format that supports debugging

---

### 4.6 Payment Processing Staff

**Role:** Payment processing staff are responsible for reviewing proof of payment (POP) documents submitted by students, confirming or rejecting payments, and maintaining accurate payment records. In a small academy, this role may be performed by the administrator directly.

**Key Concerns:**
- A clear, organised queue of payments awaiting review
- Simple one-click access to uploaded POP documents for verification
- An audit trail recording who confirmed each payment and when
- Immediate enrollment activation on payment confirmation

**Pain Points:**
- Currently, POP documents arrive via WhatsApp in an unorganised stream
- There is no audit trail for payment confirmations; disputes are difficult to resolve
- Enrollment activation after payment requires manual follow-up communication

**Success Metrics:**
- All pending POP documents visible in a single, organised payment dashboard
- POP documents accessible with one click; no need to search WhatsApp message history
- Every payment confirmation logged with the staff member's identity and a timestamp
- Enrollment status updated automatically within seconds of payment confirmation

---

### 4.7 Regulatory / Certification Body

**Role:** The regulatory or certification body sets professional standards for beauty industry credentials. They do not directly interact with the platform but have a vested interest in ensuring that certificates issued by the Bello Beauty Academy are traceable, consistently formatted, and verifiable.

**Key Concerns:**
- Each certificate must be uniquely identifiable and traceable to a specific student and course
- Certificate formatting must be professional and consistent across all awards
- The academy must be able to produce a verifiable record of any certificate on request

**Pain Points:**
- Certificates currently produced manually may vary in format, quality, and completeness
- There is no digital record or verification mechanism for certificates already issued
- The academy cannot easily confirm whether a given certificate is genuine if challenged

**Success Metrics:**
- Every certificate contains a unique certificate number stored in the platform database
- Certificate format is standardised and consistent across all courses and student records
- Certificate metadata (student name, course, issue date, unique ID) is retrievable from the database at any time for verification purposes

---


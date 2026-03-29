# 💅 Bello Beauty Academy

> A modern, full stack web-based Beauty Training Academy Management System for professional beauty certification programs.

![Platform](https://img.shields.io/badge/Platform-Web%20Application-blueviolet)
![Status](https://img.shields.io/badge/Status-In%20Development-orange)
![Architecture](https://img.shields.io/badge/Architecture-C4%20Model-blue)

---

## Overview

**Bello Beauty Academy** is a comprehensive web-based training management system designed for a professional beauty academy. The platform manages the full lifecycle of beauty education, from course browsing and student enrollment, to class scheduling, progress tracking, and digital certificate generation.

The academy does **not** offer beauty services. It is a **professional beauty training institution** offering certification courses in:

- 💁 Lash Techniques
- 👁️ Brow Artistry
- 💅 Nail Technology
- 💄 Makeup Artistry

This platform digitises and streamlines all academy operations, replacing manual administrative processes with an efficient, role-based digital management system.

---

## What This System Will Do Once Completed

Once fully developed, the Bello Beauty Academy Platform will provide a complete digital management solution for the academy. Students will be able to browse and enroll in certification courses online, upload proof of payment, access course materials, track their training progress, and download their digital certificates upon completion. Trainers will be able to manage their assigned sessions, record student attendance, and submit competency assessments through a dedicated portal. Administrators will have full control over the course catalogue, student enrollments, trainer assignments, class schedules, and payment confirmations, all from a single centralised dashboard. The system will also automatically generate and issue branded PDF certificates to students who successfully complete their courses, and dispatch email notifications at every key stage of the enrollment and payment lifecycle.

---

## 📂 Repository Structure

```
bello-beauty-academy/
│
├── README.md                        ← You are here
│
└── docs/
    ├── SPECIFICATION.md             ← Full system specification document
    ├── ARCHITECTURE.md              ← System architecture and C4 diagrams
    ├── STAKEHOLDER_ANALYSIS.md      ← Stakeholder analysis with roles, concerns, and success metrics
    ├── SYSTEM_REQUIREMENTS.md       ← Full SRD with acceptance criteria and NFR categories
    ├── REFLECTION.md                ← Reflection on translating requirements to use cases and tests
    └── TEST_AND_USE_CASE.md         ← Use case diagrams, specifications, and test cases
```

---

## 📄 Documentation

| Document | Description |
|----------|-------------|
| [SPECIFICATION.md](./docs/SPECIFICATION.md) | Full system specification including domain description, problem statement, and system scope |
| [ARCHITECTURE.md](./docs/ARCHITECTURE.md) | System architecture overview and C4 diagrams |
| [STAKEHOLDER_ANALYSIS.md](./docs/STAKEHOLDER_ANALYSIS.md) | Stakeholder analysis table with roles, concerns, pain points, and success metrics |
| [SYSTEM_REQUIREMENTS.md](./docs/SYSTEM_REQUIREMENTS.md) | Full System Requirements Document with functional requirements, acceptance criteria, NFRs, and traceability matrix |
| [REFLECTION.md](./docs/REFLECTION.md) | Reflection on challenges in translating requirements to use cases and tests |
| [TEST_AND_USE_CASE.md](./docs/TEST_AND_USE_CASE.md) | Use case diagrams, use case specifications, and test cases |

---

## ✨ Key Features

### 🎓 Student Features
- Browse and search all available certification courses
- Enroll in training courses online
- Upload proof of payment for manual verification
- View personal training schedule and class timetable
- Access course materials and learning resources
- Track personal training progress and attendance per course
- Download digital certificates upon course completion

### 👩‍🏫 Trainer Features
- View assigned courses and upcoming sessions
- Record and update student attendance per session
- Submit student competency assessment results
- Upload course materials and resources for enrolled students

### 🛠️ Administrator Features
- Create, edit, and manage all courses and categories
- Manage trainer profiles and course assignments
- Manage student enrollments and approval workflow
- Review proof of payment and confirm or reject payments
- Schedule and manage training sessions
- Generate and issue branded digital PDF certificates
- Generate operational reports on enrollments, completions, and payments

---

## Architecture Overview

The Bello Beauty Academy follows a **layered client-server architecture** with clearly separated concerns:

| Layer | Technology |
|-------|------------|
| Frontend | React.js (SPA) |
| Backend API | Node.js + Express.js |
| Database | PostgreSQL |
| Authentication | JWT + bcrypt |
| File Storage | AWS S3 |
| Certificate Generation | PDF Service (Puppeteer / PDFKit) |
| Notifications | Email Service (NodeMailer + SendGrid) |
| Hosting | Docker + Cloud Deployment |

---

## 👩‍💻 Author

Aaniquah Dicks

---

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
├── README.md                           ← You are here
│
└── docs/
    ├── SPECIFICATION.md                ← Full system specification document
    ├── ARCHITECTURE.md                 ← System architecture and C4 diagrams
    ├── STAKEHOLDER_ANALYSIS.md         ← Stakeholder analysis with roles, concerns, and success metrics
    ├── SYSTEM_REQUIREMENTS.md          ← Full SRD with acceptance criteria and NFR categories
    ├── REFLECTION.md                   ← Reflection on Kanban board and template selection
    ├── TEST_AND_USE_CASE.md            ← Use case diagrams, specifications, and test cases
    ├── AGILE_PLANNING.md               ← Agile planning document with user stories, backlog, and sprint plan
    ├── TEMPLATE_ANALYSIS.md            ← GitHub project template comparison and justification
    ├── KANBAN_EXPLANATION.md           ← Kanban board definition and workflow explanation
    ├── STATE_DIAGRAMS.md               ← Object state transition diagrams for 8 critical system objects
    ├── ACTIVITY_DIAGRAMS.md            ← Activity workflow diagrams for 8 key system workflows
    ├── ASSIGNMENT8_REFLECTION.md       ← Reflection on object state and activity workflow modeling
    ├── DOMAIN_MODEL.md                 ← Domain model with entities, attributes, methods, and business rules
    ├── CLASS_DIAGRAM.md                ← Full Mermaid.js class diagram with design decisions
    └── ASSIGNMENT9_REFLECTION.md       ← Reflection on domain modeling and class diagram development
```

---

## 📄 Documentation

| Document | Description |
|----------|-------------|
| [SPECIFICATION.md](./docs/SPECIFICATION.md) | Full system specification including domain description, problem statement, and system scope |
| [ARCHITECTURE.md](./docs/ARCHITECTURE.md) | System architecture overview and C4 diagrams |
| [STAKEHOLDER_ANALYSIS.md](./docs/STAKEHOLDER_ANALYSIS.md) | Stakeholder analysis table with roles, concerns, pain points, and success metrics |
| [SYSTEM_REQUIREMENTS.md](./docs/SYSTEM_REQUIREMENTS.md) | Full System Requirements Document with functional requirements, acceptance criteria, NFRs, and traceability matrix |
| [REFLECTION.md](./docs/REFLECTION.md) | Reflection on Agile project management including template selection and Kanban customisation |
| [TEST_AND_USE_CASE.md](./docs/TEST_AND_USE_CASE.md) | Use case diagrams, use case specifications, and test cases |
| [AGILE_PLANNING.md](./docs/AGILE_PLANNING.md) | Agile planning document with user stories, prioritised product backlog, and sprint plan |
| [TEMPLATE_ANALYSIS.md](./docs/TEMPLATE_ANALYSIS.md) | GitHub project template comparison and justification for the selected Kanban template |
| [KANBAN_EXPLANATION.md](./docs/KANBAN_EXPLANATION.md) | Kanban board implementation including board structure, workflow visualisation, and WIP limits |
| [STATE_DIAGRAMS.md](./docs/STATE_DIAGRAMS.md) | Object state transition diagrams for 8 critical system objects |
| [ACTIVITY_DIAGRAMS.md](./docs/ACTIVITY_DIAGRAMS.md) | Activity workflow diagrams for 8 key system workflows |
| [ASSIGNMENT8_REFLECTION.md](./docs/ASSIGNMENT8_REFLECTION.md) | Reflection on object state modeling and activity workflow modeling |
| [DOMAIN_MODEL.md](./docs/DOMAIN_MODEL.md) | Domain model with core entities, attributes, methods, business rules, and relationships |
| [CLASS_DIAGRAM.md](./docs/CLASS_DIAGRAM.md) | Full Mermaid.js class diagram with design decisions and multiplicity explanations |
| [ASSIGNMENT9_REFLECTION.md](./docs/ASSIGNMENT9_REFLECTION.md) | Reflection on domain modeling and class diagram development |

---

## 🗂️ Kanban Board

The project uses a customised **Kanban** board on GitHub Projects to manage all sprint tasks. The Kanban template was selected from GitHub's available project templates based on its six-column default structure, built-in WIP limit support, and flexibility to add custom columns without sprint overhead. A full comparison of the evaluated templates is available in [TEMPLATE_ANALYSIS.md](./docs/TEMPLATE_ANALYSIS.md).

📋 [View the Project Board](https://github.com/users/Aaniquah222641495/projects/5)

### Board Columns

| Column | Purpose |
|--------|---------|
| **Backlog** | All identified work not yet scheduled for the current sprint |
| **Ready** | Groomed tasks with defined acceptance criteria, ready to be picked up |
| **In Progress** | Tasks actively being developed |
| **Blocked** | Tasks that cannot progress due to an unresolved dependency or blocker |
| **In Review** | Tasks with an open pull request awaiting code review |
| **Testing** | Tasks where development is complete and being verified against acceptance criteria |
| **Done** | Tasks that have been developed, reviewed, tested, and verified |

### Customisation Choices

**Testing column:** Added to create a clear separation between completing development and verifying it against the acceptance criteria defined in the user stories. Without this column, tasks move directly from In Progress to Done and the verification step gets skipped. This is particularly important for critical flows such as the payment confirmation process and certificate generation where correctness directly affects the student experience.

**Blocked column:** Already included in the Kanban template by default. Retained and actively used to make obstacles immediately visible. A task that cannot progress should not remain in In Progress giving a false impression of activity.

**Task labels:** Each task issue carries multiple labels to categorise the type of work involved. All Sprint 1 tasks have the `Sprint 1` and `feature` labels. Tasks are additionally labelled as `backend` for API endpoint work, `frontend` for UI implementation, `notification` for email dispatch tasks, and `testing` for test-writing tasks. This makes it easy to filter the board by work type and understand the nature of each task at a glance.

**Parent user story references:** Each task issue includes a `Parent User Story: #number` reference in its description. GitHub automatically converts this into a clickable cross-reference, linking every task back to the user story it delivers. This ensures full traceability from sprint task through to the original user requirement.

**Future sprint tasks in Backlog:** Tasks T-019, T-020, and T-021 have been added to the Backlog column to represent work from Should-have and Could-have user stories that will be scheduled in future sprints. This ensures the Backlog column reflects a realistic project state with planned but not yet scheduled work.

---

##  Key Features

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
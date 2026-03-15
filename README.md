# 💅 Bello Beauty Academy

> A modern, full stack web-based Beauty Training Academy Management System for professional beauty certification programs.

![Platform](https://img.shields.io/badge/Platform-Web%20Application-blueviolet)
![Status](https://img.shields.io/badge/Status-In%20Development-orange)
![License](https://img.shields.io/badge/License-MIT-green)
![Architecture](https://img.shields.io/badge/Architecture-C4%20Model-blue)

---

## 📖 Overview

**Bello Beauty Academy** is a comprehensive web-based training management system designed for a professional beauty academy. The platform manages the full lifecycle of beauty education — from course browsing and student enrollment, to class scheduling, progress tracking, and digital certificate generation.

The academy does **not** offer beauty services. It is a **professional beauty training institution** offering certification courses in:

- 💁 Lash Techniques
- 👁️ Brow Artistry
- 💅 Nail Technology
- 💄 Makeup Artistry

This platform digitises and streamlines all academy operations, replacing manual administrative processes with an efficient, role-based digital management system.

---

## 📂 Repository Structure

```
bello-beauty-academy/
│
├── README.md                  ← You are here
│
└── docs/
    ├── SPECIFICATION.md     ← Full system specification document
    └── ARCHITECTURE.md      ← System architecture and C4 diagrams

```

---

## 📄 Documentation

| Document | Description |
|----------|-------------|
| [SPECIFICATION.md](./SPECIFICATION.md) | Full system specification including functional and non-functional requirements |
| [ARCHITECTURE.md](./ARCHITECTURE.md) | System architecture overview and C4 diagrams |

---

## ✨ Key Features

### 🎓 Student Features
- Browse and search all available certification courses
- Enroll in training courses online
- View personal training schedule and class timetable
- Access course materials and learning resources
- Track personal training progress per course
- Download digital certificates upon course completion

### 👩‍🏫 Trainer Features
- View assigned courses and sessions
- Manage training session details and content
- Record and update student attendance and progress
- Communicate with enrolled students

### 🛠️ Administrator Features
- Create, edit, and manage all courses and categories
- Manage trainer profiles and assignments
- Manage student enrollments and records
- Schedule and manage training sessions
- Generate operational and student progress reports
- Issue and manage digital certificates

---

## 🏗️ Architecture Overview

The Bello Beauty Academy follows a **layered client-server architecture** with clearly separated concerns:

| Layer | Technology |
|-------|------------|
| Frontend | React.js (SPA) |
| Backend API | Node.js + Express.js |
| Database | PostgreSQL |
| Authentication | JWT + OAuth2 |
| Certificate Generation | PDF Service (Puppeteer) |
| Notifications | Email Service (NodeMailer / SendGrid) |
| Hosting | Docker + Cloud Deployment |

---


## 👩‍💻 Author

Aaniquah Dicks

---


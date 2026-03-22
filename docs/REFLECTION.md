# REFLECTION.md — Bello Beauty Academy Platform

**Document Version:** 1.0
**Date:** March 2026
**Author:** Software Engineer / Requirements Analyst
**Assignment:** Assignment 4 — Reflection on Balancing Stakeholder Needs

---

## Reflection: Challenges in Balancing Stakeholder Needs

### Introduction

Eliciting and balancing stakeholder requirements is one of the most demanding aspects of software engineering. It is rarely a matter of simply recording what people ask for. It requires identifying where those requests conflict, understanding the real need behind each one, and making deliberate design decisions that serve the system as a whole. This reflection documents the key conflicts, trade offs, and design decisions I encountered while defining the requirements for the **Bello Beauty Academy Platform**.

---

### Conflict 1: Student Convenience vs. Administrator Payment Control

The sharpest conflict in this project was between the student's need for instant enrollment confirmation and the administrator's need to manually verify payment before granting course access.

From the student's perspective (FR05, FR12), the expectation is straightforward: enroll, pay, and get access. Any delay feels like unnecessary friction. From the administrator's perspective (FR24, FR25), activating a student's enrollment before confirming payment is a financial risk the academy cannot absorb, particularly since the current payment model is cash and EFT only with no automated gateway.

Choosing one stakeholder over the other entirely would have broken the system for the other. Giving students instant access would undermine the academy's revenue controls. Requiring administrators to manually chase every enrollment would replicate the exact bottleneck the platform was designed to solve.

**Design decision:** I introduced a formal enrollment lifecycle with three stages: pending, active, and completed. Automated email notifications are sent at every status transition. The student is notified immediately that their application is received (FR11) and again the moment payment is confirmed (FR27). I also added a structured proof of payment upload feature (FR06) so that students submit their bank slip through the platform rather than via WhatsApp. This gives payment staff a single organised dashboard (FR24) instead of a message inbox, which directly addresses the administrator's pain point without removing the payment verification step.

---

### Conflict 2: Trainer Simplicity vs. System Auditability

Trainers are beauty professionals first. During stakeholder analysis it became clear that any attendance recording interface requiring more than a few taps would simply not be used reliably. At the same time, the administrator and payment processing staff stakeholders placed a firm requirement on audit trails. Every attendance record and assessment submission needed to log who made the entry and when, so that disputes could be resolved and records trusted.

These two requirements pull in opposite directions. Adding visible audit fields such as "submitted by" labels, timestamps, and confirmation dialogs to the trainer interface would make it more cumbersome. Removing them would leave the system with unverifiable records.

**Design decision:** I resolved this by separating the audit concern from the interface entirely. Audit metadata including the trainer's user ID and submission timestamp is captured automatically at the API layer on every POST request to `/api/attendance` and `/api/assessments`, with no input required from the trainer. The trainer sees a clean, minimal interface (NFR-U01) while the administrator sees a fully audited record. The burden of compliance sits in the system, not on the user.

---

### Trade off: Security vs. Usability in Payment Document Access

Proof of payment files contain sensitive financial information. Storing them in a publicly accessible location would be a serious security failure. However, making them too difficult to access would slow down the administrator's review workflow, which is one of the primary pain points the platform was built to solve.

**Design decision:** I specified that uploaded POP files be stored in a private AWS S3 bucket, accessible only via presigned URLs with a 15-minute expiry (NFR-SEC05). From the administrator's perspective, the experience is seamless. One click on "View POP" in the payment dashboard generates the presigned URL and opens the file instantly. The security constraint is entirely invisible to the user, but the file is never publicly accessible. This is the kind of trade off that can only be resolved at the architecture level rather than the interface level.

---

### Trade off: Feature Completeness vs. Maintainability

The academy owner and the regulatory/certification body stakeholder both raised the expectation of a public certificate verification portal, giving employers or clients a way to confirm whether a certificate issued by the academy is genuine. This is a legitimate requirement traceable directly to the certification body's concern around traceability and verifiability.

However, building a public facing verification API in the first release would have introduced considerable scope and complexity for a single developer project. Including it risked delivering an incomplete core system rather than a complete and maintainable one.

**Design decision:** I deferred the public verification portal to the Future Scope section of the specification, but I designed the current system to support it without requiring any rework later. Every certificate generated by the platform is assigned a unique certificate number stored in the certificates table (FR22). When the verification portal is built in a future release, it will query that existing record with no schema changes required. This approach satisfies the spirit of the stakeholder concern now while keeping the current release scope realistic and the codebase maintainable (NFR-M01).

---

### Conclusion

What this process made clear to me is that good requirements engineering is not about satisfying every stakeholder fully. It is about making principled, documented decisions when their needs conflict. In each case above, the resolution was not a compromise that left everyone partially unhappy, but a design decision that addressed the core concern of each stakeholder without sacrificing the integrity of the system. The enrollment lifecycle, the API layer audit logging, the presigned URL pattern, and the future proof certificate schema are all direct products of stakeholder conflict. In each case, working through that conflict produced a better system than one designed without it.

---


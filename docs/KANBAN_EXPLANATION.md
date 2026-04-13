# Kanban Board Implementation: Bello Beauty Academy

**Document Version:** 1.0
**Date:** March 2026
**Assignment:** Assignment 7 — GitHub Project Templates and Kanban Board Implementation
**Status:** Final

---

## Table of Contents

1. [What is a Kanban Board](#1-what-is-a-kanban-board)
2. [Our Board Structure](#2-our-board-structure)
3. [How the Board Visualises Workflow](#3-how-the-board-visualises-workflow)
4. [Limiting Work in Progress](#4-limiting-work-in-progress)
5. [How the Board Supports Agile Principles](#5-how-the-board-supports-agile-principles)
6. [Kanban Board Screenshot](#6-kanban-board-screenshot)

---
 
📋 [View the Bello Beauty Academy Kanban Board](https://github.com/users/Aaniquah222641495/projects/5)
 
---

# 1. What is a Kanban Board

A Kanban board is a visual tool for tracking work as it moves through a development process. Work items are represented as cards and the board is divided into columns, each representing a stage in the workflow. Cards move from left to right as work progresses, eventually landing in the Done column when the task is complete.

The name Kanban comes from Japanese and means visual signal. The idea behind it is straightforward: work should be visible, limited in quantity, and flowing steadily. A well-maintained Kanban board tells anyone who looks at it exactly what the team is working on, what is waiting, and what has been finished.

---

# 2. Our Board Structure

The Bello Beauty Academy Platform board is based on the GitHub Kanban template with two custom columns added, Testing and Blocked. The columns are ordered as follows:

| Column | Purpose |
|--------|---------|
| **Backlog** | Work that has been identified but is not yet planned for the current sprint. Future tasks live here until they are ready to be scheduled. |
| **Ready** | Tasks that have been groomed, have clear acceptance criteria, and are ready for a developer to pick up. |
| **In Progress** | Tasks that are actively being worked on. |
| **Blocked** | Tasks that cannot move forward due to a dependency, missing information, or an external blocker. |
| **In Review** | Tasks where development is done and a pull request is open awaiting code review. |
| **Testing** | Tasks where the code has been reviewed and is being verified against the acceptance criteria from the user stories. |
| **Done** | Tasks that have been built, reviewed, tested, and confirmed to meet all acceptance criteria. |

---

# 3. How the Board Visualises Workflow

Every column on the board represents a real stage in the work. A task begins in Backlog or Ready, moves into In Progress when someone picks it up, then to Blocked if something prevents it from continuing, to In Review once a pull request is submitted, to Testing once the review passes, and finally to Done when everything checks out.

At any point the board answers three questions without anyone needing to ask:

- What is the team working on right now?
- What is ready to be picked up next?
- What has been completed?

The Blocked column is one of the most important signals on the board. Without it, a stuck task would sit in In Progress and look like active work when nothing is actually moving. Blocked makes the problem visible so it can be resolved before it holds up the sprint.

The Testing column exists because finishing code and verifying it are two different things. A task that moves straight from In Review to Done risks skipping the step of checking it against the acceptance criteria. Testing gives that step its own visible stage.

---

# 4. Limiting Work in Progress

WIP limits are set on the In Progress, Testing, and Blocked columns with a maximum of three tasks each. The idea is simple: the team should finish work before starting new work. If three tasks are already In Progress, no new task moves out of Ready until one of them advances.

This matters for a few reasons. It prevents the team from spreading attention too thin across too many tasks at once. It also makes bottlenecks obvious. If Testing fills up while In Progress sits empty, it is a clear signal that verification is slower than development and the team needs to focus on clearing Testing before picking up anything new.

GitHub Projects does not enforce WIP limits automatically but the current count is displayed at the top of each column, making it easy to see when a limit is being approached or exceeded.

---

# 5. How the Board Supports Agile Principles

**Continuous delivery.** Tasks move to Done as soon as they are verified rather than waiting for a batch release at the end of the sprint. This keeps value flowing steadily throughout the two-week cycle.

**Adaptability.** The Blocked column means the team can react to obstacles quickly. A blocked task does not get lost in the noise of In Progress. It is flagged immediately so the right person can address it.

**Transparency.** The board is a shared, live view of the sprint. Every team member can see the same information at the same time without checking in with anyone. This removes ambiguity about what is happening and who is responsible for what.

**Incremental progress.** Tracking each of the 18 sprint tasks individually means the team can see exactly how much has been done and how much remains at any point in the sprint, rather than finding out at the end that more work remains than expected.

---

# 6. Kanban Board Screenshot

The screenshot below shows the Bello Beauty Academy Platform Kanban board with all custom columns, linked issues, labels, task assignments, and tasks distributed across workflow stages.

<img width="1437" height="900" alt="Screenshot 2026-04-13 at 01 46 00" src="https://github.com/user-attachments/assets/4735ab2c-86b0-4c23-8494-5d9be45e73f2" />
<img width="1436" height="899" alt="Screenshot 2026-04-13 at 01 46 11" src="https://github.com/user-attachments/assets/e9c3c90f-6e08-43f9-b608-ebbad3168371" />

---


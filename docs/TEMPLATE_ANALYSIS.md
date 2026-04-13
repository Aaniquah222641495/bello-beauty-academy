# GitHub Project Template Analysis: Bello Beauty Academy

**Document Version:** 2.0
**Date:** March 2026
**Assignment:** Assignment 7 — GitHub Project Templates and Kanban Board Implementation
**Status:** Final

---

## Table of Contents

1. [GitHub Project Template Comparison](#1-github-project-template-comparison)
2. [Selected Template Justification](#2-selected-template-justification)
3. [Template Comparison Screenshot](#3-template-comparison-screenshot)

---

# 1. GitHub Project Template Comparison

GitHub Projects offers several pre-built templates to help teams manage software development workflows. The table below compares five templates that were evaluated for the Bello Beauty Academy Platform.

| Feature | Kanban | Iterative Development | Bug Tracker | Team Retrospective | Feature Release |
|---------|--------|-----------------------|-------------|-------------------|-----------------|
| **Default Columns** | Backlog, Ready, In Progress, In Review, Done | Sprint Backlog, In Progress, In Review, Done | Reported, Triaged, In Fix, Verify, Closed | Went Well, To Improve, Action Items, Done/Resolved | Discovery, Design, Build, QA, Released |
| **Number of Default Columns** | 5 | 4 | 5 | 4 | 5 |
| **Primary Purpose** | Continuous flow with WIP limits and pull-based task management | Sprint-based Scrum or Scrumban with velocity tracking across iterations | Full defect lifecycle management from triage through fix and verification | Capturing retrospective outcomes as trackable action items | Tracking all work required to ship a specific feature from idea to release |
| **Automation Support** | Supports GitHub Actions for moving items based on status changes | Native Iteration field in GitHub Projects. Velocity charts available in GitHub Insights | Pairs with GitHub Actions for SLA alerting on P0 and P1 issues | Carry-forward automation surfaces unresolved action items | Integrates with GitHub Milestones for release-level tracking |
| **WIP Limits** | Supported via column limits. Exposes bottlenecks immediately | Sprint scope commitment prevents unbounded WIP | Not applicable. Severity-based prioritisation is used instead | Not applicable. This template focuses on improvement tracking rather than delivery | Not applicable. Gated stages manage flow rather than column limits |
| **Agile Suitability** | Excellent. Highly visual and flexible. Works well for teams shipping continuously. The In Review stage and customisable columns reflect a mature development workflow. | Excellent. Directly aligned with Scrum and Scrumban. Sprint commitment, velocity tracking, and a defined cadence make this the strongest template for Agile sprint-based development. | Limited. Designed exclusively for defect management rather than feature development. Not suited for sprint planning or user story tracking. | Good as a complementary tool. Not a primary development board but valuable for continuous improvement rituals alongside a sprint board. | Good for milestone-driven releases. Less suited for ongoing iterative development as it is scoped to a single feature lifecycle rather than recurring sprints. |
| **Best For** | Teams that ship continuously and want visual flow management with flexible column configuration | Agile teams running time-boxed sprints who need predictable cadence, velocity tracking, and structured backlog grooming | Teams managing large volumes of reported bugs with severity-first triage and SLA requirements | Agile teams who want to track retrospective outcomes and improvement commitments as formal work items | Product teams releasing a specific feature who need cross-functional visibility across design, engineering, and QA |

---

# 2. Selected Template Justification

**Selected Template: Kanban**
 
📋 [View the Kanban Board](https://github.com/users/Aaniquah222641495/projects/5)

The Kanban template was the strongest fit for the Bello Beauty Academy Platform and was selected for the following reasons.

**Column structure.** The five default columns (Backlog, Ready, In Progress, In Review, Done) already cover the core of the development workflow without needing to be rebuilt from scratch. The Backlog column holds future sprint work that has been identified but not yet scheduled. The Ready column acts as a staging area for tasks that are fully groomed and waiting to be picked up, which prevents developers from pulling in tasks that are not yet properly defined. The In Review column ensures that code is reviewed before it is marked as complete, which is an important quality gate for a system handling financial transactions and certificate issuance. Two custom columns were added on top of this foundation. Testing was added to separate finishing development from verifying it against the acceptance criteria defined in the user stories. Blocked was added so that stuck tasks have a visible home rather than sitting in In Progress and giving a misleading picture of progress.

**WIP limit support.** The Kanban template has column-level WIP limits built in as a first-class feature. A limit of three tasks was applied to In Progress, Testing, and Blocked. This means that if three tasks are already in a column, no new task should enter it until one advances. This discipline prevents the team from spreading attention too thin and ensures that tasks are finished before new ones are started. It also makes bottlenecks immediately visible. If Testing reaches its limit while In Progress sits empty, it is a clear signal that verification needs attention before any new development begins.

**Low overhead.** The Iterative Development template was a strong contender because it is designed specifically for sprint-based work and includes a native Iteration field. However it also brings sprint ceremonies, velocity tracking, and a formal sprint commitment process that the project does not yet need. At this stage of the Bello Beauty Academy Platform, the team is focused on delivering the core enrollment and payment flow and does not need the additional planning infrastructure that Iterative Development introduces. The Kanban template provides the workflow visibility needed for Sprint 1 without adding process for its own sake.

**Agile fit.** The Kanban template supports the Agile principles of continuous delivery, transparency, and adaptability. Tasks move through the board continuously as work progresses rather than in batches at the end of a sprint. Blocked items surface immediately on the board rather than being discovered in a retrospective. The board gives the entire team a shared, live view of the sprint without requiring any additional reporting or status updates.

The Iterative Development template was not selected because the velocity tracking and ceremony overhead are not yet needed at this stage of the project. The Bug Tracker template is designed for defect management and does not suit a project that is still in active feature development with no existing bug backlog. The Team Retrospective template is a useful complement to a sprint board but is not designed to manage day-to-day task flow. The Feature Release template organises work around a single delivery milestone rather than ongoing iterative sprints, which does not match the development approach planned for this project.

---

# 3. Template Comparison Screenshot

The screenshot below shows the GitHub project template selection screen with the available templates.

<img width="660" height="539" alt="Screenshot 2026-04-13 at 01 47 03" src="https://github.com/user-attachments/assets/1e2e663f-8822-4d9b-889d-6149a576cdb4" /><img width="664" height="544" alt="Screenshot 2026-04-13 at 01 47 12" src="https://github.com/user-attachments/assets/a11611ee-8b8f-4646-a367-e7da6f9bd630" />




---


# Reflection on Agile Project Management: Bello Beauty Academy

**Document Version:** 1.0
**Date:** March 2026
**Assignment:** Assignment 7 — GitHub Project Templates and Kanban Board Implementation
**Status:** Final

---

## Reflection: Challenges in Selecting and Customising a Project Template

### Choosing the Right Template

The first challenge was figuring out which GitHub project template was actually the right fit for this project. At first glance the templates all look similar because they all end up as a board with columns. The differences only become clear when you think carefully about how work actually flows through a development process.

My initial instinct was to pick the most feature-rich option. I assumed more columns meant more structure and more structure meant better organisation. What I realised when I thought it through is that more columns also means more decisions to make every time a task moves. For a project in its early sprints, that kind of overhead is not helpful.

The Kanban template ended up being the right choice. The five default columns already covered the core of what the project needed without requiring a complete rebuild. The Iterative Development template was tempting because it is designed specifically for sprint-based work, but it brings velocity tracking and sprint ceremony overhead that the project is not ready for yet. The Kanban template gave the structure without the extra process.

### Customising the Board

Two columns were added: Testing and Blocked.

The decision to add Testing came from thinking about what actually happens between finishing code and closing a task. There is a real difference between a developer saying they are done and the implementation actually being verified against the acceptance criteria. Without a Testing column, that step tends to get skipped because there is nowhere specific for it to sit. Tasks jump from In Review to Done and the verification is assumed rather than confirmed.

The Blocked column was a more straightforward decision. A task that cannot move forward should not stay in In Progress giving the impression that work is happening when it is not. Having a dedicated column for blocked tasks makes the problem visible immediately so it can be dealt with before it holds up the sprint.

### Comparing GitHub Projects to Other Tools

Trello is simpler and more approachable. It is easy to get a board up and running quickly and the drag and drop experience is smooth. The downside is that it is disconnected from the codebase, so there is always a gap between what the board shows and what is actually happening in the repository.

Jira is on the other end of the scale. It has burndown charts, velocity tracking, detailed reporting, and a level of configurability that is hard to match. The problem is the complexity. Setting it up properly takes time and the learning curve is steep. For a project of this size it would be more tool than necessary.

GitHub Projects sits between the two. It is not as polished as Trello or as powerful as Jira but it has something neither of those has, which is direct integration with the repository. Issues, pull requests, and milestones are all connected. When a task references an issue number or a pull request is opened, the connection is automatic. For a project that already lives on GitHub, that integration is more valuable than any feature either of the other tools offers.




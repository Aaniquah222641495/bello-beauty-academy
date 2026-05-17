# Branch Protection Rules
## Bello Beauty Academy Platform

**Document Version:** 1.0
**Date:** May 2026
**Status:** Final

---

## Table of Contents

1. [Overview](#1-overview)
2. [Branch Protection Rules Configured](#2-branch-protection-rules-configured)
3. [Why Each Rule Matters](#3-why-each-rule-matters)
4. [Real-World DevOps Relevance](#4-real-world-devops-relevance)
5. [Pull Request Workflow](#5-pull-request-workflow)
6. [Branch Protection Settings Screenshot](#6-branch-protection-settings-screenshot)

---

## 1. Overview

Branch protection rules are settings applied to a specific branch in a GitHub repository that control how and when code can be merged into that branch. For the Bello Beauty Academy Platform, branch protection rules are configured on the `main` branch.

The `main` branch represents the stable, production-ready state of the codebase. Any code that reaches `main` must have been reviewed by at least one developer and must have passed all automated tests. Branch protection rules enforce this standard automatically, removing the possibility of human error or shortcuts under pressure.

Without branch protection, any developer could push broken code directly to `main` at any time. With branch protection active, the only way code reaches `main` is through a pull request that has been reviewed and has passed the CI pipeline.

---

## 2. Branch Protection Rules Configured

The following rules are active on the `main` branch of the Bello Beauty Academy Platform repository.

| Rule | Setting |
|------|---------|
| Require pull request before merging | Enabled |
| Required number of approvals | 1 |
| Require status checks to pass before merging | Enabled |
| Required status check | CI/CD Pipeline |
| Block direct pushes to main | Enabled |
| Enforce rules for administrators | Enabled |

### Screenshot of Branch Protection Settings

![Branch Protection Rules](./screenshots/cicd/branch-protection-rules.png)

---

## 3. Why Each Rule Matters

### 3.1 Require Pull Request Before Merging

**What it does:** Forces all code changes to go through a pull request rather than being pushed directly to `main`. A pull request creates a visible, reviewable record of every change before it is accepted.

**Why it matters:** A pull request gives the team a chance to catch bugs, logic errors, security issues, and style inconsistencies before they reach the stable branch. It also creates a permanent audit trail of who approved what and when. In the Bello Beauty Academy Platform, a bug in the enrollment or payment flow that reaches `main` could affect real students. The pull request requirement means a second pair of eyes always reviews changes to these critical flows before they land.

**Without this rule:** Any developer could push directly to `main` at any time, bypassing review entirely. A single careless commit could break the entire application for all users.

---

### 3.2 Require at Least 1 Approval

**What it does:** A pull request cannot be merged until at least one other developer has reviewed the code and clicked Approve.

**Why it matters:** Code review is one of the most effective quality assurance practices in software engineering. Reviewers catch problems the original developer missed, ask questions that expose flawed assumptions, and share knowledge across the team. Requiring at least one approval means no change is ever reviewed only by the person who wrote it.

**Without this rule:** A developer could open a pull request and immediately merge it themselves, making the review requirement meaningless.

---

### 3.3 Require Status Checks to Pass Before Merging

**What it does:** The pull request cannot be merged until the CI pipeline has run and all checks have passed. In this project, the required status check is the CI/CD Pipeline GitHub Actions workflow, which runs all 153 tests.

**Why it matters:** This rule connects the human review process to the automated test suite. Even if a reviewer approves a pull request, it still cannot be merged if the tests are failing. This means a broken change can never reach `main` regardless of how confident the developer or reviewer feels about it. The tests are the final, objective gate.

**Without this rule:** A developer could merge a pull request with failing tests as long as a reviewer approved it. Tests would exist but would have no enforcement power.

---

### 3.4 Block Direct Pushes to Main

**What it does:** Prevents any developer, including repository administrators, from pushing commits directly to `main` using `git push`. All changes must go through a pull request.

**Why it matters:** Direct pushes bypass both the review requirement and the status check requirement. If direct pushes are allowed, the entire protection system can be circumvented with a single command. Blocking direct pushes closes this loophole completely.

**Without this rule:** A developer under time pressure could bypass the pull request process entirely with a direct push, breaking the protection guarantees.

---

### 3.5 Enforce Rules for Administrators

**What it does:** Applies the same branch protection rules to repository administrators that apply to all other contributors. Administrators cannot bypass the rules.

**Why it matters:** In many teams, repository owners and administrators are the most senior developers. They are also the most likely to feel justified in pushing directly to `main` when something needs to be fixed urgently. Enforcing rules for administrators removes this exception and ensures the protection applies consistently to everyone on the team.

**Without this rule:** Administrators could bypass all protections at any time, which undermines the integrity of the entire workflow.

---

## 4. Real-World DevOps Relevance

Branch protection rules are a standard practice in professional software development teams. They are a foundational component of what is known as a trunk-based development workflow, where `main` (or `master`) is always kept in a deployable state.

In a production environment, the `main` branch is typically connected to a deployment pipeline. When code is merged to `main`, it triggers a deployment to a staging or production server automatically. If broken code could reach `main` without review or testing, it would be deployed to production automatically — potentially breaking a live service for real users.

The Bello Beauty Academy Platform's branch protection rules mirror the standards used at technology companies such as Google, Meta, and Shopify, where no code reaches a production branch without automated testing and peer review. The three rules working together — pull requests required, tests must pass, direct pushes blocked — create a safety net that is both automated and human-verified.

---

## 5. Pull Request Workflow

The following workflow applies to all contributors to the Bello Beauty Academy Platform.

```
1. Create a feature branch from main
   git checkout -b feature/my-feature

2. Make changes and commit with issue references
   git commit -m "feat: Implement new feature (#issue-number)"

3. Push the feature branch to GitHub
   git push origin feature/my-feature

4. Open a pull request targeting main on GitHub

5. GitHub automatically triggers the CI/CD Pipeline workflow
   → All 153 tests run on the pull request

6. If tests fail:
   → The pull request is blocked from merging
   → The developer must fix the failing tests and push again

7. If tests pass:
   → A reviewer is requested
   → The reviewer reads the code, leaves comments, and approves or requests changes

8. Once approved and all checks pass:
   → The pull request is merged into main
   → The CD pipeline runs and generates the release JAR artifact
```

---

## 6. Branch Protection Settings Screenshots

The screenshots below show the branch protection rules configured on the `main` branch of the Bello Beauty Academy Platform GitHub repository.

### Rule Configuration — Part 1

Shows the branch name pattern set to `main`, Require a pull request before merging enabled, and Require approvals set to 1.

![Branch Protection Rules Part 1](./screenshots/cicd/branch-protection-rules-1.png)

### Rule Configuration — Part 2

Shows Require status checks to pass before merging enabled with the Build and Test job selected as the required check, and Do not allow bypassing the above settings enabled.

![Branch Protection Rules Part 2](./screenshots/cicd/branch-protection-rules-2.png)

### Branch Protection in Action — Direct Push Blocked

The screenshot below shows the branch protection rule blocking a direct push to `main`. The error message confirms that changes must be made through a pull request and that the required status check must pass before merging.

![Branch Protection Blocked Direct Push](./screenshots/cicd/branch-protection-blocked.png)
# Reflection: Domain Modeling and Class Diagram Development
## Bello Beauty Academy Platform

**Document Version:** 1.0
**Date:** March 2026
**Status:** Draft

---

## Reflection

### 1. Challenges Faced

#### Figuring Out What Deserves Its Own Entity

The hardest part of this assignment was deciding what should be its own entity and what should just be an attribute on something else. My first draft of the domain model had `Payment` as a set of fields sitting directly on `Enrollment`, things like `paymentStatus`, `paymentMethod`, and `proofOfPaymentUrl`. That seemed fine at first. But once I started thinking about everything the payment needs to track on its own, like its own timestamps, its own confirmation record, its own rejection reason, and the fact that it goes through its own state lifecycle from `PENDING_UPLOAD` all the way to `CONFIRMED` or `REJECTED`, it became clear it needed to be a separate entity. Collapsing it into `Enrollment` would have made one class responsible for too many things.

The same question came up with `Progress`. At first I thought attendance and assessment data could just live as some kind of list on the `Enrollment` object. But progress is recorded per session, and the system needs to be able to ask things like "which students have dropped below 80% attendance this week" or "has this student passed all assessments so far." Those kinds of queries need `Progress` to be its own entity with its own relationship to both `Enrollment` and `Session`. Once I thought about the queries the system actually needs to run, the separation made sense.

#### Working Out Where Methods Belong

Defining attributes was straightforward because most of them come directly from the specification. Methods were harder because they require a judgment call about which class is responsible for what. The certificate generation was the trickiest one. The admin triggers it, but the eligibility check should not live on `Certificate` because `Certificate` does not know anything about attendance or assessments. That knowledge lives on `Enrollment`, so `isEligibleForCertificate()` belongs there. `Certificate.generate()` then gets called only after that check passes. Getting this right required thinking about which class actually owns the data a method needs to do its job.

The `Administrator` class was another one I had to be careful about. It was tempting to put a lot of business logic there because the admin is the one triggering most of the important actions. But most of the logic should live on the entities being acted on. `Administrator.confirmPayment()` should really just be a thin call that delegates to `Payment.confirm()`, which then calls `Enrollment.activate()`. The administrator is an actor, not a container for all the system's rules.

---

### 2. Alignment with Previous Assignments

One of the things I found genuinely useful about this assignment is how much it built on the work from Assignment 8. The state diagrams from that assignment modeled the lifecycle of objects like `Enrollment`, `Payment`, and `Certificate`. Now those lifecycles show up directly in the class diagram as `status` attributes and state-transition methods.

For example, the `Enrollment` class has methods `activate()`, `complete()`, `suspend()`, and `withdraw()` that map directly to the transitions in the Enrollment state diagram from Assignment 8. The `Payment` class has `uploadProof()`, `confirm()`, and `reject()` that mirror the `PENDING_UPLOAD`, `UNDER_REVIEW`, `CONFIRMED`, and `REJECTED` states exactly. This was not planned in advance, it just turned out that way because both the state diagrams and the class diagram are modeling the same real system.

The activity diagrams from Assignment 8 also helped a lot. Each workflow step in those diagrams points to a specific method in the class diagram. The Student Registration workflow maps to `User.register()` and `User.verifyEmail()`. The Enrollment and Payment workflow maps to `Student.submitEnrollment()` and `Payment.uploadProof()`. The Certificate Generation workflow maps to `Certificate.generate()`, `Certificate.issue()`, and `Certificate.download()`. Looking back at the activity diagrams while building the class diagram made it much easier to spot missing methods.

The functional requirements from the specification also stayed in view throughout. The `Certificate` entity exists because of [FR09](SPECIFICATION.md#61-student-requirements) and [FR22](SPECIFICATION.md#63-administrator-requirements). The `Progress` entity exists because of [FR08](SPECIFICATION.md#61-student-requirements), [FR15](SPECIFICATION.md#62-trainer-requirements), and [FR16](SPECIFICATION.md#62-trainer-requirements). Every entity in the model can be traced back to at least one requirement, which feels like the right standard to hold it to.

---

### 3. Trade-offs Made

#### Inheritance vs a Single User Class

The biggest structural decision in the class diagram was whether to use inheritance for the three user roles or just have a single `User` class with a `role` attribute. I went with inheritance because `Student`, `Trainer`, and `Administrator` each have attributes and methods that are genuinely different from one another and would make a single `User` class very cluttered. A student has an `emergencyContact` field and a `downloadCertificate()` method. A trainer has a `specialisation` field and a `recordAttendance()` method. Keeping those separate makes each class much easier to read and understand.

The trade-off is that inheritance is less flexible. If the academy ever needs someone who is both a student and a trainer, the current model cannot represent that cleanly. A composition-based approach where roles are separate objects attached to a user would handle that case better. For the current scope though, inheritance is simpler and makes the diagram more readable, which felt like the right call at this stage.

#### Leaving Some Entities Out

There are tables in the database schema, like `Notification` (email log) and course material download tracking, that are not in the class diagram. I made a deliberate decision to leave them out because they are infrastructure concerns rather than core domain entities. Including them would have made the diagram harder to read without helping anyone understand how the system actually works. The goal of a class diagram is to communicate structure, not to replicate the full database schema.

---

### 4. Lessons Learned

The biggest thing I took away from this assignment is that domain modeling is not something you do once and then it is finished. My first draft was much simpler and had several entities merged together in ways that would have caused problems later. Each revision came from asking the same two questions: does this model support the behaviour described in the activity diagrams, and does it enforce the business rules from the specification? Treating the model as something that gets better through questioning rather than something produced in one go is a much more realistic way to approach it.

The other thing that stuck with me is the deletion test for relationships. When I was unsure whether a relationship should be composition or association, asking "if I delete the parent, does the child still make sense on its own?" cut through the confusion quickly. A `Payment` with no `Enrollment` to belong to is meaningless, so composition is right. A `Trainer` with no courses currently assigned can still exist as a person in the system, so association is right. It is a simple rule but it consistently gave the right answer.

Finally, working through this after having done the state and activity diagrams in Assignment 8 made me realise how much the different diagram types reinforce each other. The state diagrams told me what states each object needs. The activity diagrams told me what methods each class needs. The class diagram then brought all of that together into one static picture of the system. None of the three assignments could have produced as complete a picture on its own.
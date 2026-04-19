# Reflection: Object State and Activity Workflow Modeling
## Bello Beauty Academy Platform

**Document Version:** 1.0  
**Date:** March 2026  
**Status:** Draft  

---

## Reflection

### 1. Choosing the Right Level of Detail for States and Actions

One of the hardest parts of this assignment was figuring out how detailed to make each diagram. There's a real tension between making diagrams that are thorough enough to be useful and keeping them simple enough to actually understand.

For the state diagrams, my first attempt at the Enrollment object only had three states: `Pending`, `Active`, and `Completed`. That seemed fine at first, but when I started thinking about what happens between `Pending` and `Active`, I realised there was a whole payment flow in between that the system actually needed to track. Without states like `AwaitingPayment` and `PaymentRejected`, the system has no way of knowing that a student is waiting to pay versus already paying. So I ended up adding more states than I originally planned, but they were all necessary.

On the other side, I had to stop myself from going too far. For example, I could have modelled every sub-step of the file upload process as a separate state: "file selected", "file validated", "uploading", "upload complete": but that would have made the diagram more like a low-level program flowchart than a useful state model. I settled on using functional requirements as the guide: if a state matters for how the system behaves toward a user or which features are available, it belongs in the diagram. If it's just a technical implementation detail, it doesn't.

For the activity diagrams the problem was similar but felt different in practice. Workflows naturally have a lot of steps, and it was tempting to add decision diamonds everywhere. The rule I ended up using was: only add a decision node if the workflow actually branches into meaningfully different paths. Checking whether a form field is empty doesn't need its own diamond if the only outcome is "show an error and try again": that's just part of normal validation flow. But checking whether a payment document is valid does need one, because the paths after that (confirm vs. reject vs. defer) are genuinely different in what the system and user do next.

---

### 2. Aligning Diagrams with Agile User Stories

The user stories from the previous assignments were all written from the user's perspective, describing what they want to do and why. Something like: *"As a student, I want to upload my proof of payment so that my enrollment gets confirmed."*

When I tried to map this directly to a state diagram, I realised the user story doesn't say anything about the states the system needs to manage. The story implies at least four states on the Payment object, but none of them are named in the story. I had to work backwards: thinking about what intermediate states the system needs to exist in for the user's goal to be achievable, and what can go wrong at each step.

This was actually one of the more useful exercises in the assignment. It showed me that user stories describe outcomes but leave out a lot of the system behaviour needed to deliver those outcomes. The state and activity diagrams are where that missing logic gets made explicit.

One thing I found difficult was handling edge cases. User stories almost always describe the happy path: the normal successful flow. But real systems fail in all kinds of ways: files get uploaded in the wrong format, links expire, payment amounts are wrong, trainers become unavailable. I had to go through each workflow and ask "what could go wrong here?" to make sure my activity diagrams covered those cases. Most of the alternative paths in my diagrams came from thinking about this, not from the user stories themselves.

---

### 3. State Diagrams Compared to Activity Diagrams

After working with both types of diagrams on the same system, the difference between them is clearer to me now.

State diagrams focus on a **single object** and model every state that object can be in across its whole lifetime. They're good for capturing business rules: things like "a certificate can only be downloaded once it's been issued" or "an enrollment can only become active after payment is confirmed." These rules are hard to express in any other diagram type.

Activity diagrams focus on a **process**: a sequence of steps, decisions, and actions that might involve multiple people, objects, and systems all working together. The Certificate Generation workflow, for example, involves the admin, the Progress Tracking Component, the Certificate Generation Service, AWS S3, the email service, and the student all playing a role. That kind of cross-cutting workflow is exactly what an activity diagram is designed to show.

The two complement each other rather than duplicating each other. The Payment state diagram tells you what states a payment can be in. The Admin Payment Review activity diagram tells you the step-by-step process the admin goes through to actually move the payment between those states. You need both to have a full picture.

---

### 4. Lessons Learned

The biggest thing I took away from this assignment is that **drawing diagrams forces you to be precise about things you might have glossed over when writing requirements**. While building the state diagrams, I found at least three places in my specification where I had made assumptions but never written them down: like what happens to a suspended enrollment if the student never comes back, or what the exact attendance threshold is for triggering the at-risk flag. The diagrams forced me to make those decisions explicit.

I also learned that **parallel actions in activity diagrams are more common than I expected**. I initially thought most workflows were purely sequential, but once I started thinking carefully about what actually needs to wait for what, I found multiple places where things could happen at the same time: notifications and logging especially, since they're almost always independent of each other.

Finally, using Mermaid for all of these diagrams was a good experience. Writing diagrams as code rather than drawing them in a tool means they live in the same Git repository as the rest of the project, can be reviewed in pull requests, and are automatically rendered on GitHub. That's a real benefit in a team environment, and I can see why more professional projects are moving toward diagram-as-code approaches.
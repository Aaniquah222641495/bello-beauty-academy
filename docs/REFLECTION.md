
# REFLECTION_A5 : Bello Beauty Academy Platform

**Document Version:** 2.0
**Date:** March 2026
**Assignment:** Assignment 5 — Reflection: Challenges in Translating Requirements to Use Cases and Tests
**Status:** Final

---

## Reflection: Challenges in Translating Requirements to Use Cases and Tests

Going into this assignment I thought translating requirements into use cases would be straightforward because the requirements were already written. What I quickly realised is that having requirements does not mean you automatically know how to model them. There were a number of challenges that made me think more carefully about the system than I had in any of the previous assignments.

### Knowing What Counts as a Use Case

The hardest part at the start was figuring out which things from the requirements were actually use cases and which were just system actions. For example, sending an enrollment confirmation email appeared in the requirements and I initially wanted to include it as a use case. But when I thought about it properly, I realised that no user sits down and deliberately initiates that action. It just happens automatically in the background after a student enrolls. Once I understood that a use case has to begin and end with an actor doing something meaningful, it became easier to filter out those kinds of entries. I ended up moving them into the postconditions of other use cases instead.

### Deciding How Much to Break Things Up

Another challenge was deciding how detailed each use case should be. The payment process was the most difficult one to figure out. At first I had it all as one big use case but it felt messy and the specification was hard to write clearly. I eventually split it into viewing the payment dashboard and confirming or rejecting a payment as two separate use cases, with an include relationship between them. That made the flows much cleaner and the test cases easier to write. I learned that breaking things up properly at the start saves a lot of confusion later.

### Writing the Scope Statements

I found writing the scope statements more difficult than expected. My first drafts were too vague, things like "the student accesses the schedule" without saying where or how. Once I started being specific about which button the actor clicks and which page they are on, the statements became much more useful. It also forced me to think about the interface in a way I had not before, which actually helped when thinking about what the system needs to display.

### Writing Test Cases

The negative test cases were definitely the trickiest part of the assignment. Writing a test case for something that should fail, like trying to generate a certificate before all assessments are done, required me to think about exactly what the system should say and do in that situation. I noticed that some of my requirements did not actually specify this clearly enough. Going back and fixing those gaps in the use case specifications felt like the most valuable part of the whole process because it showed me how the different documents are supposed to connect.

### Conclusion

This assignment taught me that writing good use cases and test cases is not just about documenting what the system does. It is about understanding it well enough to describe it precisely. Every time I got stuck it was because I had not thought something through clearly enough in an earlier step, which is probably the point.

---


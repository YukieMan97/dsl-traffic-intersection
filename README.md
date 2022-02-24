Add entries to this file summarising each project milestone. Don't forget that these need to have been discussed with your TA before the milestone deadline; also, remember to commit and push them by then!

### Milestone 1

**Idea Description:** A domain-specific language used to simulate vehicle traffic at simple road intersections. The user would be a city planner or someone who designs traffic systems. The DSL will help enable the user to design an efficient system for managing incoming traffic at different intersections by simulating various input combinations of roads, directions, vehicle flow rates and traffic light configurations. Given the required inputs, the DSL will output a simulation of the intersection and allow the user to see how well their system works in real time.

**TA Feedback:** The group's first discussion with the TA was not as productive as we had hoped. We hadn't yet decided on an idea and were very much still in the brainstorming phase for the project. Some of the original ideas that we came up with were: a DSL for generating floorplans and blueprints, a DSL to help schedule restaurant bookings, and a DSL for designing logic circuits. However, we couldn't very easily figure out ways in which we could increase the complexity of the DSLs to allow for "infinite possibilites" for inputs. The DSLs were all very form-like and even after a lot of discussion with the TA, we couldn't settle on one that we thought worked well. After the meeting, we decided to brainstorm some fresh ideas and finally decided on the intersection idea, which we thought fit the scope of the project quite well, and allowed for dynamic inputs which was a major requirement. We followed up with the TA by email once we had fleshed out the idea further. The TA approved our idea and provided some suggestions on how to possibly increase complexity of the DSL further.

**Follow Up Tasks & Features:** We need to meet up as a group (and maybe with the TA) to discuss some extra features that we could add to the DSL to increase the complexity of the project a bit further. Some ideas that we may consider adding would be pedestrians and crossings, different vehicle types, and even diversions due to construction on the road. We also need to think about how the input grammer might look like and define the semantics of the DSL.

### Milestone 2

**Division of Responsibilities:** We have decided on the following main responsibilities for the project.
1. Tokenization and Parsing = Avi + Jeremy
2. Validation = Eric + Yukie
3. Simulation Rendering = Avi
4. Vehicle Behaviour = Yukie
5. Road and Lane Generation = Jeremy
6. Traffic Lights Behaviour = Eric
7. (optional) Pedestrian Behavior / Diversions / Extra Features = Whole Group

Although we have selected these areas and assigned specific people to them, we anticipate having to work closely with each other as a group in order to connect the different parts together and build a cohesive DSL program.

**Project Roadmap:** We have been using Google Docs to keep track of key project goals and features, and since we anticipate having to make adjustments to our project roadmap multiple times in the future, we have decided to add that to the document as well. Please visit [Google Docs](https://docs.google.com/document/d/1jGF1smF9ydmUK7W9-2vRUcxLClmz8b5uWgSN2eE8x2A/edit?usp=sharing) to view the complete project roadmap (near the bottom of the document).

**Progress Summary:** Since last week, we have met up as a group a few times and communicated over Discord in order to make changes to the project idea. We took into account both the TA feedback we recieved over our meeting this week, as well as our own thoughts and ideas. We realized that we were able to focus much more and come up with better ideas since we were not simply brainstorming rough ideas as we were last week. We mostly decided to cut down on a lot of extra features which were not really adding too much value to our DSL. We have decided to focus more on the dynamic features such as traffic light configurations and vehicle spawning behaviour. However, if we do end up having time at the end of the project, we would love to implement some of the features that we have cut off such as pedestrians and diversions. We have also completed defining the input grammar using EBNF (can also be seen in the Google Docs) as well as a sample input of a program. We still intend on making changes to this grammar to be more in line with our revised idea for the project.

### Milestone 3

**Language Design (EBNF):** 
![ebnf](https://i.gyazo.com/eb02d1d7d751919ba0947b2520556aa8.png)

**User Study Notes:** User study #1: An individual found the direction portion of our EBNF a little bit confusing. Once we had explain how the directions were supposed to work, the individual understood. Other than that, this individual believes the rest of the EBNF is intuitive enough.

**Changes to Original Language Design:** We will discuss and consider a better input strategy for directions. The rest of the language design will remain as is. We plan to have more user studies so that we can improve our EBNF before implementing the parser and validator. 

### Milestone 4
**Status of Implementation**
1. Tokenization and Parsing has been completed by Jeremy. A few tweaks may be needed after reviewing more user studies
2. Validation will be completed by Eric.
3. Evaluation will be completed by Yukie using the visitor pattern.
4. Simulation Rendering is currently being implemented by Avi.
5. Road and Lane Generation = Jeremy
6. Traffic Lights Behaviour = Eric 
7. (optional) Pedestrian Behavior / Diversions / Extra Features = Whole Group
8. Note: 5-7 have not been discussed in depth yet.

**Plans for Final User Study**
We are still looking for users to go over our EBNF. Currently, they are referring to a sample input while learning the EBNF. Once we have more of the program completed, we plan to allow individuals to try it out. This will allow us further improve our program.

**Planned Timeline For Remaining Days**
We hope to finish the program before Wednesday, Oct 14th. This will give us 4-5 days for testing, completing user studies, and changing our implementation if needed. 

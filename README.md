## Name
progressbot

## About
This bot helps you to track progress of your projects. Because application is hosted on free hosting it periodically goes offline after 30 minutes of inactivity. Normally it should stay online from 7am till 12pm Amsterdam time zone. If you need it in off-work hours visit this link to wake it up: https://prgrssbot.herokuapp.com/wakeup. It may take around 10-15 second till app wakes up, shows "At your service" message and is ready to take commands.

## Description
You create a project using command /new, submit your progress with /submit, follow it with /active and complete with /complete. Project can be anything from a book with known number of pages or a video course with limited number of lectures

## Homepage
https://prgrssbot.herokuapp.com

## Keep alive endpoint
https://prgrssbot.herokuapp.com/wakeup

### Supported commands
The list as it should be sent to PodFather:
```
new - create new project
submit - submit current progress
complete - complete project
cancel - cancel project
active - show active projects
completed - show completed projects
cancelled - show cancelled projects
```

### Interaction state lives for 5 minutes

---
title: Data Model
description: "UML class diagram, Entity-relationship diagram and DDL."
menu: Data Model
order: 20
ddl: sql/ddl.sql
erd:
  image: img/erd.svg
  pdf: pdf/erd.pdf
uml:
  image: img/teamle uml.svg
  pdf: pdf/teamle uml.pdf
---

{% include ddc-abbreviations.md %}
{% include uml.md %}
{% include erd.md %}
{% include ddl.md %}

## Entity classes
- ['edu.cnm.deepdive.teamle.model.entity.GameResult'](https://github.com/ddc-java-17/teamle-Tarlet1/blob/main/app/src/main/java/edu/cnm/deepdive/teamle/model/entity/GameResult.java)
- ['edu.cnm.deepdive.teamle.model.entity.User'](https://github.com/ddc-java-17/teamle-Tarlet1/blob/main/app/src/main/java/edu/cnm/deepdive/teamle/model/entity/User.java)

## Dao interfaces
- ['edu.cnm.deepdive.teamle.model.dao.GameResultDao'](https://github.com/ddc-java-17/teamle-Tarlet1/blob/main/app/src/main/java/edu/cnm/deepdive/teamle/model/dao/GameResultDao.java)
- ['edu.cnm.deepdive.teamle.model.dao.UserDao'](https://github.com/ddc-java-17/teamle-Tarlet1/blob/main/app/src/main/java/edu/cnm/deepdive/teamle/model/dao/UserDao.java)

## DataBase class
- ['edu.cnm.deepdive.teamle.service.TeamleDatabase'](https://github.com/ddc-java-17/teamle-Tarlet1/blob/main/app/src/main/java/edu/cnm/deepdive/teamle/service/TeamleDatabase.java)
package com.headissue.fate.service.api

import com.headissue.fate.model.{Aspect, Actor, HasAspects, HasCharacters, IsContent, Mook, World}

import scala.collection.mutable

trait FateService {
  // TODO v2
  // def lookupInviteLinks(world: World): InviteLinks
  // def assignGameMaster(world: World, newGameMaster: Player): World
  // def deleteWorld(world: World)

  // TODO

  // def deleteWorld(world: World)
  // needs generic properties on characters
  // def setVisibility(hasVisibility: HasVisibility): HasVisibility
  // def changeWorldName(world: World): World
  // def kickPlayerFromWorld(player: Player, world: World): Player
  // def setMaxPlayers(world: World): World
  // def resolveScene(scene: Scene): Scene

  /**
    * looks up world by name or creates a new one and returns it
    */
  def enterWorld(name: String): World

  def updateWorldDescription(world: World, newDescription: String): World

  /**
    * adds scenes, scenarios etc
    */
  def addContent(content: IsContent): IsContent

  def createCharacter: Actor

  def updateCharacter(character: Actor): Actor

  def addCharacterTo(hasCharacters: HasCharacters, character: Actor): Actor

  def getCharacters(hasCharacters: HasCharacters): mutable.Buffer[Actor]

  // FIXME aspects cant be without a world or character
  def createAspect: Aspect

  // wird createAspect und da steht der zusammenhang drin
  def addAspectTo(hasAspects: HasAspects, aspect: Aspect): Aspect

  def updateAspect(aspect: Aspect): Aspect

  def getAspects(hasAspects: HasAspects): mutable.Buffer[Aspect]

  def removeAspectFrom(hasAspects: HasAspects, aspect: Aspect): Aspect

  def changeAspectToCharacter(aspect: Aspect, hasAspects: HasAspects, hasCharacters: HasCharacters): Actor

  def createMook(owningCharacterId: Long): Mook

  def updateMook(mook: Mook): Mook

  def getMooks(character: Actor): mutable.Buffer[Mook]
}

package com.headissue.fate.service.api

import com.headissue.fate.model.{Aspect, Character, HasAspects, HasCharacters, IsContent, Mook, World}

import scala.collection.mutable

trait FateService {

  def enterWorld(name: String): World

  // TODO v2
  // def lookupInviteLinks(world: World): InviteLinks
  // def assignGameMaster(world: World, newGameMaster: Player): World
  // def deleteWorld(world: World)

  // TODO
  // def deleteWorld(world: World)
  // def setVisibility(hasVisibility: HasVisibility): HasVisibility
  // def changeWorldName(world: World): World
  // def kickPlayerFromWorld(player: Player, world: World): Player
  // def setMaxPlayers(world: World): World
  // def resolveScene(scene: Scene): Scene


  def updateWorldDescription(world: World, newDescription: String): World

  def addContent(content: IsContent): IsContent

  def createCharacter: Character

  def updateCharacter(character: Character): Character

  def addCharacterTo(hasCharacters: HasCharacters, character: Character): Character

  def getCharacters(hasCharacters: HasCharacters): mutable.Buffer[Character]

  def createAspect: Aspect

  def addAspectTo(hasAspects: HasAspects, aspect: Aspect): Aspect

  def updateAspect(aspect: Aspect): Aspect

  def getAspects(hasAspects: HasAspects): mutable.Buffer[Aspect]

  def removeAspectFrom(hasAspects: HasAspects, aspect: Aspect): Aspect

  def changeAspectToCharacter(aspect: Aspect, hasAspects: HasAspects, hasCharacters: HasCharacters): Character

  def createMook(owningCharacterId: Long): Mook

  def updateMook(mook: Mook): Mook

  def getMooks(character: Character): mutable.Buffer[Mook]
}

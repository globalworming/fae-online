package com.headissue.fate.service.api

import com.headissue.fate.model.{Actor, Aspect, HasAspects, HasCharacters, IsContainer, IsContent, Mook, World}

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
  // def invitePlayerTo(campaign: Campaign, email: String):
  /**
    * enter and exit scenes
    */
  // def enter(hasCharacter: hasCharacter, character: Character):

  def createWorld(name: String): World

  def getWorldInfo(name: String): World

  def setWorldInfo(world: World): World

  def updateWorldDescription(world: World, newDescription: String): World

  /**
    * adds scenes, scenarios etc one at a time
    */
  def addContent(content: IsContent): IsContent

  def createCharacter: Actor

  def createContent(isContent: IsContent, isContainer: IsContainer): IsContent

  def updateCharacter(character: Actor): Actor

  def addRoleTo(hasCharacters: HasCharacters, character: Actor): Actor

  def getCharacters(hasCharacters: HasCharacters): mutable.Buffer[Actor]

  /**
    * create a new aspect and link it
    */
  def addAspectTo(hasAspects: HasAspects, newAspect: Aspect): Aspect

  def updateAspect(aspect: Aspect): Aspect

  def getAspects(hasAspects: HasAspects): mutable.Buffer[Aspect]

  def removeAspectFrom(hasAspects: HasAspects, aspect: Aspect): Aspect

  def changeAspectToCharacter(aspect: Aspect, hasAspects: HasAspects, hasCharacters: HasCharacters): Actor

  def createMook(owningCharacterId: Long): Mook

  def updateMook(mook: Mook): Mook

  def getMooks(character: Actor): mutable.Buffer[Mook]
}

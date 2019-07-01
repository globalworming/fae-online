package com.headissue.fate.service.api

import com.headissue.fate.model.{Character, HasCharacters, IsContent, World}

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


  def updateWorldDescription(world: World, newDescription: String): World

  def addContent(content: IsContent): IsContent

  def createCharacter: Character

  def addCharacterTo(hasCharacters: HasCharacters, character: Character): Character

  def getCharacters(hasCharacters: HasCharacters): mutable.Buffer[Character]


}

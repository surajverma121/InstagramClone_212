package com.example.instragramclone.Models

class Reel {

    var ReelUrl :String = ""
    var caption:String = ""
    var profileLink :String? = null

    constructor()
    constructor(ReelUrl: String, caption: String) {
        this.ReelUrl = ReelUrl
        this.caption = caption
    }

    constructor(ReelUrl: String, caption: String, profileLink: String) {
        this.ReelUrl = ReelUrl
        this.caption = caption
        this.profileLink = profileLink
    }
}
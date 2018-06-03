package com.mohan.prepare.model

import com.mohan.prepare.extensions.legnthOf

class News(val id:String,val title:String,val redirectionUrl:String="",var desc:String=""){
    
    
    val subTitle:String
        get() {
           return desc legnthOf 100
        }
}
/*
*
* {
      "id": "1",
      "title": "IPL 2018: Royal Challengers Bangalore better than before, says Vettori",
      "subTitle": "No disrespect to past RCB teams because we've had some very successful ones, but we feel we are more balanced than we have ever been and that is mainly down to the amount of Indian fast bowlers and all-rounders that we have",
      "redirectionUrl": "https://timesofindia.indiatimes.com/sports/cricket/ipl/top-stories/ipl-2018-royal-challengers-bangalore-better-than-before-says-vettori/articleshow/63399712.cms"
    }
*
* */
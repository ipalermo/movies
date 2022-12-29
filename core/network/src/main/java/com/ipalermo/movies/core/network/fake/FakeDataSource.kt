package com.ipalermo.movies.core.network.fake

import com.ipalermo.movies.core.network.model.NetworkMovieResource
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.intellij.lang.annotations.Language
import java.time.LocalDate

/* ktlint-disable max-line-length */

object FakeDataSource {

    val sampleResource = NetworkMovieResource(
        id = 436270,
        title = "Black Adam",
        overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
        posterPath = "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
        releaseDate = LocalDate.of(2022, 10, 19)
    )

    @Language("JSON")
    val data = """
        [
        		{
        			"adult": false,
        			"backdrop_path": "/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
        			"genre_ids": [
        				28,
        				14,
        				878
        			],
        			"id": 436270,
        			"original_language": "en",
        			"original_title": "Black Adam",
        			"overview": "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
        			"popularity": 12157.649,
        			"poster_path": "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
        			"release_date": "2022-10-19",
        			"title": "Black Adam",
        			"video": false,
        			"vote_average": 6.8,
        			"vote_count": 1191
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/olPXihyFeeNvnaD6IOBltgIV1FU.jpg",
        			"genre_ids": [
        				27,
        				9648,
        				53
        			],
        			"id": 882598,
        			"original_language": "en",
        			"original_title": "Smile",
        			"overview": "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying new reality.",
        			"popularity": 4162.737,
        			"poster_path": "/aPqcQwu4VGEewPhagWNncDbJ9Xp.jpg",
        			"release_date": "2022-09-23",
        			"title": "Smile",
        			"video": false,
        			"vote_average": 6.8,
        			"vote_count": 695
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/yYrvN5WFeGYjJnRzhY0QXuo4Isw.jpg",
        			"genre_ids": [
        				28,
        				12,
        				878
        			],
        			"id": 505642,
        			"original_language": "en",
        			"original_title": "Black Panther: Wakanda Forever",
        			"overview": "Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T’Challa’s death. As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross and forge a new path for the kingdom of Wakanda.",
        			"popularity": 3747.097,
        			"poster_path": "/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
        			"release_date": "2022-11-09",
        			"title": "Black Panther: Wakanda Forever",
        			"video": false,
        			"vote_average": 7.5,
        			"vote_count": 803
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/yNib9QAiyaopUJbaayKQ2xK7mYf.jpg",
        			"genre_ids": [
        				18,
        				28,
        				10752
        			],
        			"id": 966220,
        			"original_language": "uk",
        			"original_title": "Снайпер. Білий ворон",
        			"overview": "Mykola is an eccentric pacifist who wants to be useful to humanity. When the war begins at Donbass, Mykola’s naive world is collapsing as the militants kill his pregnant wife and burn his home to the ground. Recovered, he makes a cardinal decision and gets enlisted in a sniper company. Having met his wife’s killers, he emotionally breaks down and arranges “sniper terror” for the enemy. He’s saved from a senseless death by his instructor who himself gets mortally wounded. The death of a friend leaves a “scar” and Mykola is ready to sacrifice his life.",
        			"popularity": 2726.003,
        			"poster_path": "/lZOODJzwuQo0etJJyBBZJOSdZcW.jpg",
        			"release_date": "2022-05-03",
        			"title": "Sniper: The White Raven",
        			"video": false,
        			"vote_average": 7.4,
        			"vote_count": 94
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
        			"genre_ids": [
        				27,
        				53
        			],
        			"id": 663712,
        			"original_language": "en",
        			"original_title": "Terrifier 2",
        			"overview": "After being resurrected by a sinister entity, Art the Clown returns to Miles County where he must hunt down and destroy a teenage girl and her younger brother on Halloween night.  As the body count rises, the siblings fight to stay alive while uncovering the true nature of Art's evil intent.",
        			"popularity": 2241.046,
        			"poster_path": "/b6IRp6Pl2Fsq37r9jFhGoLtaqHm.jpg",
        			"release_date": "2022-10-06",
        			"title": "Terrifier 2",
        			"video": false,
        			"vote_average": 7,
        			"vote_count": 624
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/tUBN1paMQ1tmVA5Sjy1ZjPWVsiF.jpg",
        			"genre_ids": [
        				12,
        				16,
        				35,
        				10751,
        				14
        			],
        			"id": 676701,
        			"original_language": "es",
        			"original_title": "Tadeo Jones 3: La Tabla Esmeralda",
        			"overview": "Tad would love for his archeologist colleagues to accept him as one of their own, but he always messes everything up. Tad accidentally destroys a sarcophagus and unleashes an ancient spell endangering the lives of his friends: Mummy, Jeff and Belzoni. With everyone against him and only helped by Sara, he sets off on an adventure that will take him from Mexico to Chicago and from Paris to Egypt, in order to put an end to the curse of the Mummy.",
        			"popularity": 2152.979,
        			"poster_path": "/jvIVl8zdNSOAJImw1elQEzxStMN.jpg",
        			"release_date": "2022-08-24",
        			"title": "Tad the Lost Explorer and the Emerald Tablet",
        			"video": false,
        			"vote_average": 8,
        			"vote_count": 88
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/u9Io1hVnxb2b3T0z99aOK8sq0SN.jpg",
        			"genre_ids": [
        				9648,
        				12,
        				80
        			],
        			"id": 829280,
        			"original_language": "en",
        			"original_title": "Enola Holmes 2",
        			"overview": "Now a detective-for-hire like her infamous brother, Enola Holmes takes on her first official case to find a missing girl, as the sparks of a dangerous conspiracy ignite a mystery that requires the help of friends — and Sherlock himself — to unravel.",
        			"popularity": 1948.197,
        			"poster_path": "/tegBpjM5ODoYoM1NjaiHVLEA0QM.jpg",
        			"release_date": "2022-11-04",
        			"title": "Enola Holmes 2",
        			"video": false,
        			"vote_average": 7.7,
        			"vote_count": 978
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/aQOWnw3HydsCQZ10O0wvxFOaWc3.jpg",
        			"genre_ids": [
        				36,
        				28,
        				18
        			],
        			"id": 551271,
        			"original_language": "en",
        			"original_title": "Medieval",
        			"overview": "The story of 14th century Czech icon and warlord Jan Zizka who defeated armies of the Teutonic Order and the Holy Roman Empire.",
        			"popularity": 2205.836,
        			"poster_path": "/4njdAkiBdC5LnFApeXSkFQ78GdT.jpg",
        			"release_date": "2022-09-08",
        			"title": "Medieval",
        			"video": false,
        			"vote_average": 7.2,
        			"vote_count": 143
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/b2FxWOxe9K7ZZ1uaPOze2RJ1ajq.jpg",
        			"genre_ids": [
        				27,
        				28,
        				35
        			],
        			"id": 675054,
        			"original_language": "es",
        			"original_title": "MexZombies",
        			"overview": "A group of teenagers must face a zombie apocalypse, and help reestablish order.",
        			"popularity": 1545.628,
        			"poster_path": "/85zufUxhD97k2s5Bu2u101Qd8Sg.jpg",
        			"release_date": "2022-10-26",
        			"title": "MexZombies",
        			"video": false,
        			"vote_average": 7.2,
        			"vote_count": 81
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/gVecEIEZLZg3pV5CACXAB48I6au.jpg",
        			"genre_ids": [
        				80,
        				18,
        				9648,
        				53
        			],
        			"id": 862965,
        			"original_language": "en",
        			"original_title": "Emily the Criminal",
        			"overview": "Emily, who is saddled with student debt and locked out of the job market due to a minor criminal record, gets involved in a credit card scam that pulls her into the criminal underworld of Los Angeles, ultimately leading to deadly consequences.",
        			"popularity": 1696.974,
        			"poster_path": "/iZvzMpREGiqDQ5eYbx8z70qPgst.jpg",
        			"release_date": "2022-08-12",
        			"title": "Emily the Criminal",
        			"video": false,
        			"vote_average": 6.9,
        			"vote_count": 187
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/f9g3R9gq0bASip9RnK2H56dbb5j.jpg",
        			"genre_ids": [
        				53
        			],
        			"id": 979924,
        			"original_language": "en",
        			"original_title": "On the Line",
        			"overview": "A provocative and edgy radio host must play a dangerous game of cat and mouse with a mysterious caller who's kidnapped his family and is threatening to blow up the whole station.",
        			"popularity": 1620.86,
        			"poster_path": "/jVmWI8PqoVTHCnrLYAcyrclzeY0.jpg",
        			"release_date": "2022-10-31",
        			"title": "On the Line",
        			"video": false,
        			"vote_average": 6.5,
        			"vote_count": 121
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/tIX6j3NzadlwGcJ52nuWdmtOQkg.jpg",
        			"genre_ids": [
        				27,
        				53,
        				9648
        			],
        			"id": 717728,
        			"original_language": "en",
        			"original_title": "Jeepers Creepers: Reborn",
        			"overview": "Forced to travel with her boyfriend to a horror festival, Laine begins to experience disturbing visions associated with the urban legend of The Creeper. As the festival arrives and the blood-soaked entertainment builds to a frenzy, she becomes the center of it while something unearthly has been summoned.",
        			"popularity": 1612.054,
        			"poster_path": "/qVVegwPsW6n9unBtLWq1rzOutka.jpg",
        			"release_date": "2022-09-15",
        			"title": "Jeepers Creepers: Reborn",
        			"video": false,
        			"vote_average": 5.8,
        			"vote_count": 471
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/707thQazLJiYLBhCrZlRoV05NNL.jpg",
        			"genre_ids": [
        				28,
        				18,
        				53
        			],
        			"id": 948276,
        			"original_language": "fr",
        			"original_title": "Balle perdue 2",
        			"overview": "Having cleared his name, genius mechanic Lino has only one goal in mind: getting revenge on the corrupt cops who killed his brother and his mentor.",
        			"popularity": 1741.174,
        			"poster_path": "/uAeZI1JJbLPq7Bu5dziH7emHeu7.jpg",
        			"release_date": "2022-11-10",
        			"title": "Lost Bullet 2",
        			"video": false,
        			"vote_average": 7.1,
        			"vote_count": 97
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/1DBDwevWS8OhiT3wqqlW7KGPd6m.jpg",
        			"genre_ids": [
        				53
        			],
        			"id": 985939,
        			"original_language": "en",
        			"original_title": "Fall",
        			"overview": "For best friends Becky and Hunter, life is all about conquering fears and pushing limits. But after they climb 2,000 feet to the top of a remote, abandoned radio tower, they find themselves stranded with no way down. Now Becky and Hunter’s expert climbing skills will be put to the ultimate test as they desperately fight to survive the elements, a lack of supplies, and vertigo-inducing heights",
        			"popularity": 1326.651,
        			"poster_path": "/spCAxD99U1A6jsiePFoqdEcY0dG.jpg",
        			"release_date": "2022-08-11",
        			"title": "Fall",
        			"video": false,
        			"vote_average": 7.3,
        			"vote_count": 1753
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/mqsPyyeDCBAghXyjbw4TfEYwljw.jpg",
        			"genre_ids": [
        				10752,
        				18,
        				28
        			],
        			"id": 49046,
        			"original_language": "de",
        			"original_title": "Im Westen nichts Neues",
        			"overview": "Paul Baumer and his friends Albert and Muller, egged on by romantic dreams of heroism, voluntarily enlist in the German army. Full of excitement and patriotic fervour, the boys enthusiastically march into a war they believe in. But once on the Western Front, they discover the soul-destroying horror of World War I.",
        			"popularity": 1263.027,
        			"poster_path": "/hYqOjJ7Gh1fbqXrxlIao1g8ZehF.jpg",
        			"release_date": "2022-10-07",
        			"title": "All Quiet on the Western Front",
        			"video": false,
        			"vote_average": 7.8,
        			"vote_count": 835
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/sqVxhRkPwOo6jogWZjE99V1HRE1.jpg",
        			"genre_ids": [
        				16,
        				35,
        				10751
        			],
        			"id": 1037858,
        			"original_language": "en",
        			"original_title": "The Soccer Football Movie",
        			"overview": "When mysterious green slime monsters start popping out of soccer balls, all-star athletes Zlatan Ibrahimović and Megan Rapinoe must team up with their four biggest fans to stop evil scientist \"Weird Al\" Yankovic from stealing their talent.",
        			"popularity": 1257.899,
        			"poster_path": "/sWoYDNPNZs5MtzPbirXV73tIHrA.jpg",
        			"release_date": "2022-11-09",
        			"title": "The Soccer Football Movie",
        			"video": false,
        			"vote_average": 7,
        			"vote_count": 33
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/5GA3vV1aWWHTSDO5eno8V5zDo8r.jpg",
        			"genre_ids": [
        				27,
        				53
        			],
        			"id": 760161,
        			"original_language": "en",
        			"original_title": "Orphan: First Kill",
        			"overview": "After escaping from an Estonian psychiatric facility, Leena Klammer travels to America by impersonating Esther, the missing daughter of a wealthy family. But when her mask starts to slip, she is put against a mother who will protect her family from the murderous “child” at any cost.",
        			"popularity": 1034.096,
        			"poster_path": "/pHkKbIRoCe7zIFvqan9LFSaQAde.jpg",
        			"release_date": "2022-07-27",
        			"title": "Orphan: First Kill",
        			"video": false,
        			"vote_average": 6.8,
        			"vote_count": 1305
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/pGx6O6IwqADOsgmqWzPysmWnOyr.jpg",
        			"genre_ids": [
        				28,
        				14
        			],
        			"id": 732459,
        			"original_language": "en",
        			"original_title": "Blade of the 47 Ronin",
        			"overview": "In this sequel to \"47 Ronin,\" a new class of warriors emerges among the Samurai clans to keep a sought-after sword from falling into the wrong hands.",
        			"popularity": 1077.524,
        			"poster_path": "/kjFDIlUCJkcpFxYKtE6OsGcAfQQ.jpg",
        			"release_date": "2022-10-25",
        			"title": "Blade of the 47 Ronin",
        			"video": false,
        			"vote_average": 6.9,
        			"vote_count": 79
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/y4XBYLldCLuNLVeObTndfAaUrc3.jpg",
        			"genre_ids": [
        				28,
        				80,
        				53
        			],
        			"id": 896485,
        			"original_language": "fr",
        			"original_title": "Overdose",
        			"overview": "Captain Sara Bellaiche, from Toulouse Judiciary Police branch, is investigating a go-fast linked to the murder of two teenagers, an investigation led by Richard Cross, from the Paris Criminal Brigade. Forced to collaborate in order to find the murderer and stop the bloody go-fast, Sara and Richard are both thrown in a breathless race against the clock on the roads of Spain and France.",
        			"popularity": 1102.01,
        			"poster_path": "/9RvM4wawLRlX608FgZr9kL8CLmy.jpg",
        			"release_date": "2022-11-04",
        			"title": "Overdose",
        			"video": false,
        			"vote_average": 6.5,
        			"vote_count": 78
        		},
        		{
        			"adult": false,
        			"backdrop_path": "/iVtpnbPE91vmi3LmcOXycEblwPA.jpg",
        			"genre_ids": [
        				10749,
        				35
        			],
        			"id": 833097,
        			"original_language": "en",
        			"original_title": "Falling for Christmas",
        			"overview": "A newly engaged and spoiled hotel heiress finds herself in the care of a handsome, blue-collar lodge owner and his precocious daughter after getting total amnesia in a skiing accident.",
        			"popularity": 882.762,
        			"poster_path": "/6GkphwL9s6dZb3DoQS9OQ4LcPgY.jpg",
        			"release_date": "2022-11-10",
        			"title": "Falling for Christmas",
        			"video": false,
        			"vote_average": 6.7,
        			"vote_count": 223
        		}
        	]
        """.trimIndent()

}

/* ktlint-enable max-line-length */

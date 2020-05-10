DROP TABLE IF EXISTS "tbLogin";
CREATE TABLE IF NOT EXISTS "tbLogin" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"login"	VARCHAR,
	"password"	VARCHAR,
	"user_id"	VARCHAR
);
DROP TABLE IF EXISTS "tbUser";
CREATE TABLE IF NOT EXISTS "tbUser" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"first_name"	VARCHAR,
	"second_name"	VARCHAR,
	"city_id"	VARCHAR,
	"first_login"	VARCHAR,
	"email"	VARCHAR
);
DROP TABLE IF EXISTS "tbEventType";
CREATE TABLE IF NOT EXISTS "tbEventType" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	INTEGER
);
DROP TABLE IF EXISTS "tbEvent";
CREATE TABLE IF NOT EXISTS "tbEvent" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"title"	TEXT,
	"event_type"	INTEGER,
	"description"	TEXT,
	"address"	TEXT,
	"longitude"	NUMERIC,
	"latitude"	NUMERIC,
	"date_start"	TEXT,
	"gallery_id"	INTEGER,
	"city_id"	INTEGER
);
DROP TABLE IF EXISTS "tbBodyParam";
CREATE TABLE IF NOT EXISTS "tbBodyParam" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"param_type"	INTEGER,
	"value"	NUMERIC,
	"created_at"	TEXT
);
DROP TABLE IF EXISTS "tbBodyParamType";
CREATE TABLE IF NOT EXISTS "tbBodyParamType" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT
);
DROP TABLE IF EXISTS "tbGalleryItem";
CREATE TABLE IF NOT EXISTS "tbGalleryItem" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT,
	"image_path"	TEXT,
	"gallery_id"	INTEGER
);
DROP TABLE IF EXISTS "tbGallery";
CREATE TABLE IF NOT EXISTS "tbGallery" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT
);
DROP TABLE IF EXISTS "tbWellnessClub";
CREATE TABLE IF NOT EXISTS "tbWellnessClub" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"title"	TEXT NOT NULL,
	"rating"	NUMERIC,
	"description"	TEXT,
	"address"	TEXT,
	"longitude"	NUMERIC,
	"latitude"	NUMERIC,
	"open_hour"	NUMERIC,
	"close_hour"	NUMERIC,
	"gallery_id"	INTEGER,
	"city_id"	INTEGER
);
DROP TABLE IF EXISTS "tbCountry";
CREATE TABLE IF NOT EXISTS "tbCountry" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	INTEGER
);
DROP TABLE IF EXISTS "tbCity";
CREATE TABLE IF NOT EXISTS "tbCity" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT UNIQUE,
	"country_id"	INTEGER
);
DROP TABLE IF EXISTS "tbSettings";
CREATE TABLE IF NOT EXISTS "tbSettings" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"key"	TEXT NOT NULL UNIQUE,
	"value"	TEXT
);
INSERT INTO "tbEventType" VALUES (1,'indoor team');
INSERT INTO "tbEventType" VALUES (2,'outdoor water');
INSERT INTO "tbEventType" VALUES (3,'outdoor team');
INSERT INTO "tbEventType" VALUES (4,'indoor personal');
INSERT INTO "tbEventType" VALUES (5,'outdoor personal');
INSERT INTO "tbEvent" VALUES (1,'ACVC: Indoor Volleyball Training, Beginner-Intermediate',1,'Auckland Central Volleyball Club presents Indoor Volleyball Training! Improve your indoor skills with indoor volleyball training! ','Auckland Grammar School, Epsom',-36.869188,174.768333,'10/07/2019 19:00',11,3);
INSERT INTO "tbEvent" VALUES (2,'PADI Openwater Diver - Learn to Dive',2,'f you’ve always wanted to take scuba diving lessons, experience unparalleled adventure and see the world beneath the waves, this is where it starts. Get your scuba diving certification with the PADI Open Water Diver course – the world’s most popular and widely recognised scuba course.','Beaumont and Gaunt St, Westhaven',-36.843763,174.753412,'12/07/2019',15,3);
INSERT INTO "tbEvent" VALUES (3,'Loaded Tough Guy and Gal Challenge',3,'The events are open to both male and female competitors who are 13 years of age and older. Challenge yourself on a course that includes swamp crossings, a spiders web net climb, crawl under barb wire, beautiful native bush trails, tunnels, hurdles, a climbing frame not to mention, mud mud and more mud!','124-164 Rautawhiri Rd, Helensville',-36.680851,174.463592,'14/07/2019',16,3);
INSERT INTO "tbEvent" VALUES (4,'Aotea Square Ice Rink',4,'Enjoy an exciting variety of special events including DJ nights, themed parties, skating lessons and much more.
Take a spin or a slide on the ice in the heart of the city and experience the magic of your very own winter playground.','Aotea Square, Queen St, CBD',-36.852087,174.763104,'16/07/2019',12,3);
INSERT INTO "tbEvent" VALUES (5,'Little Tiger Karate classes',1,'JKS Shotokan North Shore karate club has been in Browns bay for 11 years, The classes are lead by Chief instructor Sean Joseph - 4th Dan.','Baha''i Centre, 712 Beach Rd, Browns Bay',-36.719737,174.741815,'18/07/2019 09:00',14,3);
INSERT INTO "tbEvent" VALUES (6,'Krav Maga Israeli Self-Defence Class',1,'We teach Krav Maga, a progressive eclectic hand to hand system which originates from Israel. Our training consists of tried and tested self-defence concepts involving many situations including striking, grappling, ground fighting and defence against weapon attacks and threats.','Blockhouse Bay Community Centre, 524 Blockhouse Bay Rd, Blockhouse Bay',-36.922055,174.701603,'19/07/2019  10:00',13,3);
INSERT INTO "tbEvent" VALUES (7,'Parkrun Porirua',3,'The start occurs at the far end of the park''s carpark, off Champion street opposite intersection with Windley Street. Head to end of carpark where the asphalt ends and there is a gate.','Bothamley Park, Champion Street, Porirua - Mana',-41.132713,174.855298,'15/07/2019  10:00',19,4);
INSERT INTO "tbEvent" VALUES (8,'Dragon Boat - Have a Go Day',2,'Open to anyone 12+ (we have paddlers that are 70+!) and with reasonable confidence in the water. We will provide boats, paddles, lifejacket, support boat, instruction, and experienced paddlers to partner with. You bring a change of clothes, some friends, family, and a big smile.','Whairepo Lagoon, Frank Kitts Park, Wellington Waterfron',-41.288339,174.778883,'16/07/2019 12:00',17,4);
INSERT INTO "tbEvent" VALUES (9,'Lorna Jane Pop Up',3,'Martinborough, we are popping up near you!','Waihinga Community Centre, 19 Kitchener Street, Martinborough, Martinborough',-41.213881,175.457648,'16/07/2019 12:00',18,4);
INSERT INTO "tbEvent" VALUES (10,'Tinytown Buggy Walk - Houghton Bay',3,'Enjoy the company of other parents on guided buggy walks around the Southern and Eastern suburbs every 3rd Thursday of each month. Adore Wellington’s stunning sea views and join us for a free coffee and muffin courtesy of Porse.','Kilbirnie Recreation Centre, 101 Kilbirnie Crescent, Kilbirnie',-41.316161,174.794463,'18/07/2019 11:00',20,4);
INSERT INTO "tbEvent" VALUES (11,'Discover the Ancient Healing Art Of Tao Yin',5,'Tao Yin is over 6,000 years old and is the most original form of Qigong. It is the foundation from which all others emerged. Tao Yin is also a shared ancestor of yoga. Tao Yin was very formative in the evolution of yoga and helped to integrate Chinese and Indian wisdom into a powerful movement practice.','Abberley Park Hall, 15 Abberley Cres, St Albans',-43.513254,172.631726,'20/07/2019 11:00',21,5);
INSERT INTO "tbEvent" VALUES (12,'Grand National Festival of Racing',5,'Discover the wonder of winter racing with three days of fashion, food and thrilling racing at Riccarton Park.

','Riccarton Racecourse, Racecourse Road, Sockburn',-43.532373,172.549125,'21/07/2019 11:00',24,5);
INSERT INTO "tbEvent" VALUES (13,'A day out with Park Rangers: Bottle Lake Forest',3,'Join the Park Rangers and learn about forest survival! Build a bivouac and a bug motel, learn how to spot pests and, for the very brave, eat a huhu grub!','Bottle Lake Forest Park, Waitikiri drive, Burwood',-43.444312,172.687283,'16/07/2019 10:00',23,5);
INSERT INTO "tbEvent" VALUES (14,'Colour Zone Run',3,'This is a 5km run/walk or crawl however you want to get around the course, in what was called the Burwood Residential Red Zone, but being totally renamed ''"Colour Zone". Just come dressed in white and end up in so much colour.','Donnell Sports Park, Brooker Avenue, Burwood',-43.491423,172.69705,'22/07/2019 10:00',22,5);
INSERT INTO "tbBodyParamType" VALUES (1,'weight');
INSERT INTO "tbBodyParamType" VALUES (2,'blood pressure');
INSERT INTO "tbBodyParamType" VALUES (3,'blood sugar');
INSERT INTO "tbBodyParamType" VALUES (4,'pulse');
INSERT INTO "tbGalleryItem" VALUES (1,'The health space','Wellness clubs/Auckland/The health space',1);
INSERT INTO "tbGalleryItem" VALUES (2,'Snap Fitness New Lynn 24/7','Wellness clubs/Auckland/Snap Fitness',2);
INSERT INTO "tbGalleryItem" VALUES (3,'One five one','Wellness clubs/Auckland/One five one',3);
INSERT INTO "tbGalleryItem" VALUES (4,'Les Mills Newmarket','Wellness clubs/Auckland/Les Mills Newmarket',4);
INSERT INTO "tbGalleryItem" VALUES (5,'Jetts eden terrace','Wellness clubs/Auckland/Jetts eden terrace',5);
INSERT INTO "tbGalleryItem" VALUES (6,'Health & Sports Fitness Club','Wellness clubs/Auckland/Health & Sports Fitness Club',6);
INSERT INTO "tbGalleryItem" VALUES (7,'Club Physical Te Atatu','Wellness clubs/Auckland/Club Physical Te Atatu',7);
INSERT INTO "tbGalleryItem" VALUES (8,'Club Physical Birkenhead','Wellness clubs/Auckland/Club Physical Birkenhead',8);
INSERT INTO "tbGalleryItem" VALUES (9,'City fitness','Wellness clubs/Auckland/City fitness',9);
INSERT INTO "tbGalleryItem" VALUES (10,'Anytime fitness','Wellness clubs/Auckland/Anytime fitness',10);
INSERT INTO "tbGalleryItem" VALUES (11,'ACVC: Indoor Volleyball Training, Beginner-Intermediate','Sport Events/Auckland/Epsom Volleyball',11);
INSERT INTO "tbGalleryItem" VALUES (12,'Aotea Square Ice Rink','Sport Events/Auckland/Aotea Square Ice Rink',12);
INSERT INTO "tbGalleryItem" VALUES (13,'Krav Maga Israeli','Sport Events/Auckland/Krav Maga Israeli',13);
INSERT INTO "tbGalleryItem" VALUES (14,'Little Tiger Karate classes','Sport Events/Auckland/Little Tiger Karate classes',14);
INSERT INTO "tbGalleryItem" VALUES (15,'PADI Openwater Diver','Sport Events/Auckland/PADI Openwater Diver',15);
INSERT INTO "tbGalleryItem" VALUES (16,'Tough Guy and Gal Challenge','Sport Events/Auckland/Tough Guy and Gal Challenge',16);
INSERT INTO "tbGalleryItem" VALUES (17,'Dragon Boat','Sport Events/Wellington/Dragon Boat',17);
INSERT INTO "tbGalleryItem" VALUES (18,'Lorna Jane Pop Up','Sport Events/Wellington/Lorna Jane Pop Up',18);
INSERT INTO "tbGalleryItem" VALUES (19,'Parkrun Porirua','Sport Events/Wellington/Parkrun Porirua',19);
INSERT INTO "tbGalleryItem" VALUES (20,'Tinytown Buggy Walk','Sport Events/Wellington/Tinytown Buggy Walk',20);
INSERT INTO "tbGalleryItem" VALUES (21,'Ancient Healing Art Of Tao','Sport Events/Christchurch/Ancient Healing Art Of Tao',21);
INSERT INTO "tbGalleryItem" VALUES (22,'Colour Zone Run','Sport Events/Christchurch/Colour Zone Run',22);
INSERT INTO "tbGalleryItem" VALUES (23,'day out with Park Rangers','Sport Events/Christchurch/day out with Park Rangers',23);
INSERT INTO "tbGalleryItem" VALUES (24,'Grand National Festival of Racing','Sport Events/Christchurch/Grand National Festival of Racing',24);
INSERT INTO "tbGalleryItem" VALUES (25,'CityFitness','Wellness clubs/Wellington/CityFitness',25);
INSERT INTO "tbGalleryItem" VALUES (26,'Habit Majestic','Wellness clubs/Wellington/CityFitness',26);
INSERT INTO "tbGalleryItem" VALUES (27,'Jetts Wellington CBD','Wellness clubs/Wellington/Jetts Wellington CBD',27);
INSERT INTO "tbGalleryItem" VALUES (28,'Snap Fitness Johnsonville','Wellness clubs/Wellington/Snap Fitness Johnsonville',28);
INSERT INTO "tbGalleryItem" VALUES (29,'Synergy Health Fitness Club','Wellness clubs/Wellington/Synergy Health Fitness Club',29);
INSERT INTO "tbGalleryItem" VALUES (30,'Adapted Yoga and Pilates Homebase','Wellness clubs/Christchurch/Adapted Yoga and Pilates Homebase',30);
INSERT INTO "tbGalleryItem" VALUES (31,'Corporate Wellness Solutions','Wellness clubs/Christchurch/Corporate Wellness Solutions',31);
INSERT INTO "tbGalleryItem" VALUES (32,'Total Fitness','Wellness clubs/Christchurch/Total Fitness',32);
INSERT INTO "tbGallery" VALUES (1,'The health space');
INSERT INTO "tbGallery" VALUES (2,'Snap Fitness New Lynn 24/7');
INSERT INTO "tbGallery" VALUES (3,'One five one');
INSERT INTO "tbGallery" VALUES (4,'Les Mills Newmarket');
INSERT INTO "tbGallery" VALUES (5,'Jetts eden terrace');
INSERT INTO "tbGallery" VALUES (6,'Health & Sports Fitness Club');
INSERT INTO "tbGallery" VALUES (7,'Club Physical Te Atatu');
INSERT INTO "tbGallery" VALUES (8,'Club Physical Birkenhead');
INSERT INTO "tbGallery" VALUES (9,'City fitness');
INSERT INTO "tbGallery" VALUES (10,'Anytime fitness');
INSERT INTO "tbGallery" VALUES (11,'ACVC: Indoor Volleyball Training, Beginner-Intermediate');
INSERT INTO "tbGallery" VALUES (12,'Aotea Square Ice Rink');
INSERT INTO "tbGallery" VALUES (13,'Krav Maga Israeli');
INSERT INTO "tbGallery" VALUES (14,'Little Tiger Karate classes');
INSERT INTO "tbGallery" VALUES (15,'PADI Openwater Diver');
INSERT INTO "tbGallery" VALUES (16,'Tough Guy and Gal Challenge');
INSERT INTO "tbGallery" VALUES (17,'Dragon Boat');
INSERT INTO "tbGallery" VALUES (18,'Lorna Jane Pop Up');
INSERT INTO "tbGallery" VALUES (19,'Parkrun Porirua');
INSERT INTO "tbGallery" VALUES (20,'Tinytown Buggy Walk');
INSERT INTO "tbGallery" VALUES (21,'Ancient Healing Art Of Tao');
INSERT INTO "tbGallery" VALUES (22,'Colour Zone Run');
INSERT INTO "tbGallery" VALUES (23,'day out with Park Rangers');
INSERT INTO "tbGallery" VALUES (24,'Grand National Festival of Racing');
INSERT INTO "tbGallery" VALUES (25,'CityFitness');
INSERT INTO "tbGallery" VALUES (26,'Habit Majestic');
INSERT INTO "tbGallery" VALUES (27,'Jetts Wellington CBD');
INSERT INTO "tbGallery" VALUES (28,'Snap Fitness Johnsonville');
INSERT INTO "tbGallery" VALUES (29,'Synergy Health Fitness Club');
INSERT INTO "tbGallery" VALUES (30,'Adapted Yoga and Pilates Homebase');
INSERT INTO "tbGallery" VALUES (31,'Corporate Wellness Solutions');
INSERT INTO "tbGallery" VALUES (32,'Total Fitness');
INSERT INTO "tbWellnessClub" VALUES (4,'The health space',4.5,'A functional gym and personal training studio that uses research to give you the best results for a healthy lifestyle.  With over 10 years in the fitness industry Logan Reardon has developed a training system with his team that will keep you fit for life.  Our philosophy is to keep you healthy right into your retirement. Suited for all fitness levels Logan & his team are here to help you with your results.','12 Kawakawa Pl, Whenuapai',-36.807097,174.601329,5.15,19,1,3);
INSERT INTO "tbWellnessClub" VALUES (5,'Jetts Eden Terrace',4.3,'A fully equipped gym','96 New North Rd, Eden Terrace',-36.855645,174.731162,12,19,5,3);
INSERT INTO "tbWellnessClub" VALUES (6,'Health & Sports Fitness Club',3.8,'Health&Sports is the largest fitness club in Kingsland. Having just installed the world’s best gym equipment by Technogym, we are proud to provide our members with the best of the best and be the largest Technogym equipped gym in New Zealand.','2A Morningside Dr, Kingsland',-36.874505,174.736954,5.15,19,6,3);
INSERT INTO "tbWellnessClub" VALUES (7,'CityFitness',4.2,'At CityFitness we are here to help you achieve your health, wellness and fitness goals. Members of CityFitness clubs and gyms are achieving their fitness goals in our clubs all over New Zealand.','184 Karangahape Rd',-36.857996,174.759971,0,24,9,3);
INSERT INTO "tbWellnessClub" VALUES (8,'Les Mills Newmarket',4.5,'At Les Mills Newmarket you’ll find that newer really is better. Visit our award-winning health and fitness club and you’ll find new machines and more classes, which means more high-quality workout options for you. ','269 Khyber Pass Rd, Newmarket',-36.866214,174.770718,5.3,22,4,3);
INSERT INTO "tbWellnessClub" VALUES (9,'ONE FIVE ONE Health Club',5,' If you are looking for a CBD health club with something a bit special, you have found it. Uncrowded facilities which include Swimming pool, tennis court, the latest gym equipment, fitness classes, soccer, spa and cold plunge pool, sauna, steam room, complimentary towel service and the use of a range of toiletries in the changing rooms, we’ve got it all.','Level 8, SAP Tower 151 Queen St',-36.846921,174.765451,5.3,21,3,3);
INSERT INTO "tbWellnessClub" VALUES (10,'Club Physical Birkenhead',4.1,'We are a leading provider of health club facilities, offering state-of-the-art equipment, best-in-class personal trainers, and innovative GroupX fitness classes.','35 Mokoia Rd, Birkenhead',-36.810967,174.724515,5,21,8,3);
INSERT INTO "tbWellnessClub" VALUES (11,'Club Physical Te Atatu',3.7,'We are a leading provider of health club facilities, offering state-of-the-art equipment, best-in-class personal trainers, and innovative GroupX fitness classes.','278 Te Atatu Rd, Te Atatu South',-36.862843,174.647594,5,22,7,3);
INSERT INTO "tbWellnessClub" VALUES (12,'Anytime Fitness',4.3,'If you’re looking to join a supportive, welcoming gym community, with people of all fitness levels who want to see you succeed, you’ve come to the right place.','192 Onehunga Mall, Onehunga',-36.922063,174.785071,0,24,10,3);
INSERT INTO "tbWellnessClub" VALUES (13,'Snap Fitness New Lynn 24/7',4.5,'20 minutes. 16 movements. 1 killer workout. MYFIT is our signature workout that blends functional fitness movements and intensity interval training for a workout that delivers results on your schedule.','4034-4038 Great North Rd, Kelston',-36.906009,174.66132,0,24,2,3);
INSERT INTO "tbWellnessClub" VALUES (14,'Snap Fitness 24/7 Johnsonville',4.9,'20 minutes. 16 movements. 1 killer workout. MYFIT is our signature workout that blends functional fitness movements and intensity interval training for a workout that delivers results on your schedule.','Unit 5/33-39 Johnsonville Rd, Johnsonville',-41.222994,174.807834,0,24,28,4);
INSERT INTO "tbWellnessClub" VALUES (15,'Jetts Wellington CBD',3.9,'An international gym chain',' First Floor, James Smith Corner, 49-61 Cuba St, Te Aro',-41.290479,174.777437,10,19,27,4);
INSERT INTO "tbWellnessClub" VALUES (16,'Habit Majestic',4.7,'he Habit network began in 2003 as a single health, fitness and rehabilitation centre in central Wellington’s Johnston Street. A market leader at the time, Habit delivered a premium gym solution with integrated Physiotherapy, Nutrition and Personal Training. This naturally lead to other services to provide a wrap-around care model including Psychology and Occupational Therapy.',' Majestic Centre Level P2, 100 Willis St, Wellington City',-41.288229,174.774941,6,21,26,4);
INSERT INTO "tbWellnessClub" VALUES (17,'CityFitness Wellington',4.1,'At CityFitness we are here to help you achieve your health, wellness and fitness goals. Members of CityFitness clubs and gyms are achieving their fitness goals in our clubs all over New Zealand.','36-52 High St, Lower Hutt 6023',-41.211119,174.899823,0,24,25,4);
INSERT INTO "tbWellnessClub" VALUES (18,'Synergy Health & Fitness Club',4.6,'At Synergy Health & Fitness Club we cater for the busy professional who works around the Terrace or lives nearby. If you like to get out of the office and exercise during the working day our commitment to no overcrowding means you’ll always be able to get a workout, sauna or a swim in. ','Atrium Towers 154 The Terrace',-41.284752,174.773863,6,21,29,4);
INSERT INTO "tbWellnessClub" VALUES (19,'Corporate Wellness Solutions',4.8,'Providing a welcoming and user-friendly boutique facility, where members can find support to achieve their goals. With a team of highly experienced personal trainers to guide you on your health and fitness journey, we are driven by the commitment and success of our members. ','182 Barbadoes St, Christchurch Central',-43.535562,172.646036,6,20,31,5);
INSERT INTO "tbWellnessClub" VALUES (20,'Total Fitness',5,'Whether you want to get fit, lose weight, tone up or improve your posture from sitting at a desk all day, we have the experienced team and competitive packages to help you achieve your goals!','104 Victoria St, Christchurch Central',-43.522925,172.629863,6,19.3,32,5);
INSERT INTO "tbWellnessClub" VALUES (21,'Adapted Yoga and Pilates Homebase',5,'Here at Adapted we empower everyday people. No matter your age, experience or physical capability, we will work with you to find out what you need and help you become the best you can be.  In fact our motto is: "Everyday people becoming their best".','Level 1 Westside House, 34 Yaldhurst Rd, Sockburn',-43.530476,172.568607,8.3,19,30,5);
INSERT INTO "tbCity" VALUES (3,'Auckland',1);
INSERT INTO "tbCity" VALUES (4,'Wellington',1);
INSERT INTO "tbCity" VALUES (5,'Christchurch',1);
INSERT INTO "tbSettings" VALUES (1,'first_run','true');
INSERT INTO "tbSettings" VALUES (2,'eula','<h2>End-User License Agreement (EULA) of <span class="app_name">My Wellbeing Helper</span></h2>

<p>This End-User License Agreement ("EULA") is a legal agreement between you and <span class="company_name">student software</span></p>

<p>This EULA agreement governs your acquisition and use of our <span class="app_name">My Wellbeing Helper</span> software ("Software") directly from <span class="company_name">student software</span> or indirectly through a <span class="company_name">student software</span> authorized reseller or distributor (a "Reseller").</p>

<p>Please read this EULA agreement carefully before completing the installation process and using the <span class="app_name">My Wellbeing Helper</span> software. It provides a license to use the <span class="app_name">My Wellbeing Helper</span> software and contains warranty information and liability disclaimers.</p>

<p>If you register for a free trial of the <span class="app_name">My Wellbeing Helper</span> software, this EULA agreement will also govern that trial. By clicking "accept" or installing and/or using the <span class="app_name">My Wellbeing Helper</span> software, you are confirming your acceptance of the Software and agreeing to become bound by the terms of this EULA agreement.</p>

<p>If you are entering into this EULA agreement on behalf of a company or other legal entity, you represent that you have the authority to bind such entity and its affiliates to these terms and conditions. If you do not have such authority or if you do not agree with the terms and conditions of this EULA agreement, do not install or use the Software, and you must not accept this EULA agreement.</p>

<p>This EULA agreement shall apply only to the Software supplied by <span class="company_name">student software</span> herewith regardless of whether other software is referred to or described herein. The terms also apply to any <span class="company_name">student software</span> updates, supplements, Internet-based services, and support services for the Software, unless other terms accompany those items on delivery. If so, those terms apply. This EULA was created by <a href="https://www.eulatemplate.com">EULA Template</a> for <span class="app_name">My Wellbeing Helper</span>.

<h3>License Grant</h3>

<p><span class="company_name">student software</span> hereby grants you a personal, non-transferable, non-exclusive licence to use the <span class="app_name">My Wellbeing Helper</span> software on your devices in accordance with the terms of this EULA agreement.</p>

<p>You are permitted to load the <span class="app_name">My Wellbeing Helper</span> software (for example a PC, laptop, mobile or tablet) under your control. You are responsible for ensuring your device meets the minimum requirements of the <span class="app_name">My Wellbeing Helper</span> software.</p>

<p>You are not permitted to:</p>

<ul>
<li>Edit, alter, modify, adapt, translate or otherwise change the whole or any part of the Software nor permit the whole or any part of the Software to be combined with or become incorporated in any other software, nor decompile, disassemble or reverse engineer the Software or attempt to do any such things</li>
<li>Reproduce, copy, distribute, resell or otherwise use the Software for any commercial purpose</li>
<li>Allow any third party to use the Software on behalf of or for the benefit of any third party</li>
<li>Use the Software in any way which breaches any applicable local, national or international law</li>
<li>use the Software for any purpose that <span class="company_name">student software</span> considers is a breach of this EULA agreement</li>
</ul>

<h3>Intellectual Property and Ownership</h3>

<p><span class="company_name">student software</span> shall at all times retain ownership of the Software as originally downloaded by you and all subsequent downloads of the Software by you. The Software (and the copyright, and other intellectual property rights of whatever nature in the Software, including any modifications made thereto) are and shall remain the property of <span class="company_name">student software</span>.</p>

<p><span class="company_name">student software</span> reserves the right to grant licences to use the Software to third parties.</p>

<h3>Termination</h3>

<p>This EULA agreement is effective from the date you first use the Software and shall continue until terminated. You may terminate it at any time upon written notice to <span class="company_name">student software</span>.</p>

<p>It will also terminate immediately if you fail to comply with any term of this EULA agreement. Upon such termination, the licenses granted by this EULA agreement will immediately terminate and you agree to stop all access and use of the Software. The provisions that by their nature continue and survive will survive any termination of this EULA agreement.</p>

<h3>Governing Law</h3>

<p>This EULA agreement, and any dispute arising out of or in connection with this EULA agreement, shall be governed by and construed in accordance with the laws of <span class="country">nz</span>.</p>');
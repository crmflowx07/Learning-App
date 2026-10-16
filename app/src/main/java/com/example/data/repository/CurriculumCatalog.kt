package com.example.data.repository

import com.example.data.model.*

object CurriculumCatalog {

    val stages: List<StageInfo> = listOf(
        StageInfo(
            stageId = 1,
            name = "Magical Playground Island",
            urduName = "جادوئی کھیل کا جزیرہ",
            ageMin = 4,
            ageMax = 5,
            levelLabel = "Prep / KG",
            worldTheme = "Island with floating balloons, toy castles, and rainbow slides",
            worldDescription = "Explore fun sounds, complete A-B-C-D letters, numbers 1-10, shapes, and colorful stories!",
            companionName = "Pip the Bunny",
            companionType = "bunny",
            landmarks = listOf(
                Landmark("s1_lm1", "ABC Alphabet Castle", "حروفِ تہجی قلعہ", "English", "treehouse", 0.25f, 0.35f, "course_s1_eng"),
                Landmark("s1_lm2", "Number & Shape Island", "گنتی و اشکال", "Math", "castle", 0.70f, 0.30f, "course_s1_math"),
                Landmark("s1_lm3", "Urdu Phonics Bay", "اردو حروف و آوازیں", "Urdu", "bay", 0.35f, 0.75f, "course_s1_urdu"),
                Landmark("s1_lm4", "Manners Garden", "پیارے آداب و دعائیں", "Islamic Studies", "garden", 0.80f, 0.70f, "course_s1_manners")
            )
        ),
        StageInfo(
            stageId = 2,
            name = "Friendly Jungle Village",
            urduName = "دوستانہ جنگل بستی",
            ageMin = 6,
            ageMax = 7,
            levelLabel = "Class 1 - 2",
            worldTheme = "Lush green treehouse canopy with river rafts and friendly wildlife",
            worldDescription = "Master addition, subtraction, clock time, word reading, nature, and first computer steps!",
            companionName = "Koko the Monkey",
            companionType = "monkey",
            landmarks = listOf(
                Landmark("s2_lm1", "Math Safari River", "ریاضی ندی (جمع و تفریق)", "Math", "river", 0.30f, 0.30f, "course_s2_math"),
                Landmark("s2_lm2", "Reading Camp", "الفاظ کی دنیا", "English", "tent", 0.75f, 0.35f, "course_s2_eng"),
                Landmark("s2_lm3", "Urdu Kahani Grove", "کہانیوں کا باغ", "Urdu", "book", 0.25f, 0.70f, "course_s2_urdu"),
                Landmark("s2_lm4", "Tech Treehut", "کمپیوٹر تعارف و پرزے", "Computers", "computer", 0.75f, 0.75f, "course_s2_tech")
            )
        ),
        StageInfo(
            stageId = 3,
            name = "Adventure Town",
            urduName = "مہم جوئی کا قصبہ",
            ageMin = 8,
            ageMax = 9,
            levelLabel = "Class 3 - 4",
            worldTheme = "Steampunk windmill town with cogwheels, tracks, and science labs",
            worldDescription = "Conquer multiplication tables, division, plant biology, and block coding loops!",
            companionName = "Bolt the Robot Pup",
            companionType = "robopup",
            landmarks = listOf(
                Landmark("s3_lm1", "Multiplication Mill", "ضرب و تقسیم کی چکی", "Math", "mill", 0.28f, 0.32f, "course_s3_math"),
                Landmark("s3_lm2", "Science Observatory", "سائنس و کائنات", "Science", "telescope", 0.72f, 0.28f, "course_s3_sci"),
                Landmark("s3_lm3", "Block Code Workshop", "بلاک کوڈنگ منطق", "Coding", "blocks", 0.50f, 0.65f, "course_s3_code")
            )
        ),
        StageInfo(
            stageId = 4,
            name = "Science Valley",
            urduName = "وادیٔ سائنس",
            ageMin = 10,
            ageMax = 11,
            levelLabel = "Class 5 - 6",
            worldTheme = "Eco-valley powered by crystal geysers and wind turbines",
            worldDescription = "Explore fractions, algebra foundations, human body systems, and typing mastery.",
            companionName = "Nova the Drone",
            companionType = "drone",
            landmarks = listOf(
                Landmark("s4_lm1", "Algebra Geyser", "الجبرائی فوارہ", "Math", "geyser", 0.30f, 0.35f, "course_s4_math"),
                Landmark("s4_lm2", "Ecosystem Biosphere", "انسانی جسم و ماحولیات", "Science", "dome", 0.70f, 0.40f, "course_s4_sci"),
                Landmark("s4_lm3", "Logic Game Arena", "الگورتھم کھیل", "Computer", "gamepad", 0.50f, 0.78f, "course_s4_code")
            )
        ),
        StageInfo(
            stageId = 5,
            name = "Explorer City",
            urduName = "تلاش کاروں کا شہر",
            ageMin = 12,
            ageMax = 13,
            levelLabel = "Class 7 - 8",
            worldTheme = "Modern neon-lit skyline with skybridges and monorails",
            worldDescription = "Dive into real Python code (print, variables, if-else, loops), physics, and financial basics.",
            companionName = "Echo the Cyber Cat",
            companionType = "cybercat",
            landmarks = listOf(
                Landmark("s5_lm1", "Python Coding Tower", "پائیتھن کوڈنگ ٹاور", "Computer Science", "tower", 0.30f, 0.30f, "course_s5_python"),
                Landmark("s5_lm2", "Physics Particle Hub", "طبیعیات لیب (نیوٹن کے قوانین)", "Science", "atom", 0.75f, 0.32f, "course_s5_phys"),
                Landmark("s5_lm3", "Finance & Logic Exchange", "مالیاتی بصیرت و فیصد", "Finance", "chart", 0.52f, 0.72f, "course_s5_fin")
            )
        ),
        StageInfo(
            stageId = 6,
            name = "Academy Campus",
            urduName = "اکیڈمی کیمپس",
            ageMin = 14,
            ageMax = 15,
            levelLabel = "Matric (Class 9 - 10)",
            worldTheme = "Grand university quad with marble halls, lecture domes, and competitive arenas",
            worldDescription = "Board exam preparation: Matriculation Mathematics, Python functions, Web HTML, and Databases.",
            companionName = "Aegis Assistant",
            companionType = "ai_bot",
            landmarks = listOf(
                Landmark("s6_lm1", "Matric Math Amphitheater", "میٹرک ریاضی (کواڈریٹک و میٹرکس)", "Math", "hall", 0.28f, 0.35f, "course_s6_math"),
                Landmark("s6_lm2", "CS & Web Matrix", "کمپیوٹر سائنس و ایچ ٹی ایم ایل", "Computer Science", "chip", 0.72f, 0.35f, "course_s6_cs"),
                Landmark("s6_lm3", "Board Exam Colosseum", "امتحانی مشق گاہ (پاسٹ پیپرز)", "Exam Prep", "trophy", 0.50f, 0.75f, "course_s6_exam")
            )
        ),
        StageInfo(
            stageId = 7,
            name = "Career Skyline City",
            urduName = "کیریئر اسکائی لائن",
            ageMin = 16,
            ageMax = 17,
            levelLabel = "Intermediate (FSc / ICS)",
            worldTheme = "Metropolis of towering cyber spires, research labs, and entrance exam test towers",
            worldDescription = "ICS Track: C++ Object-Oriented Programming (OOP), Functions, Classes, and ECAT / FAST test prep.",
            companionName = "Vector Mentor",
            companionType = "ai_mentor",
            landmarks = listOf(
                Landmark("s7_lm1", "ICS Spire (C++ & OOP)", "آئی سی ایس (C++ اور کلاسز)", "ICS", "spire", 0.28f, 0.32f, "course_s7_ics"),
                Landmark("s7_lm2", "Pre-Engineering Complex", "پری انجینئرنگ فزکس", "Pre-Eng", "gear", 0.74f, 0.30f, "course_s7_preeng"),
                Landmark("s7_lm3", "Entry Test Launchpad", "اینٹری ٹیسٹ سینٹر (ECAT/FAST)", "Entry Test", "rocket", 0.50f, 0.74f, "course_s7_entry")
            )
        ),
        StageInfo(
            stageId = 8,
            name = "Tech Campus & Cyber Lab",
            urduName = "ٹیک کیمپس و سائبر لیب",
            ageMin = 18,
            ageMax = 22,
            levelLabel = "BSCS (8 Semesters)",
            worldTheme = "Futuristic silicon campus with holographic terminals, data vaults, and quantum servers",
            worldDescription = "Complete university BSCS degree: Digital Logic, DSA, Sorting, AI, SQL Databases, and Capstone FYP.",
            companionName = "Nexus AI",
            companionType = "quantum_ai",
            landmarks = listOf(
                Landmark("s8_lm1", "Core Code Lab (Sem 1-2)", "پروگرامنگ و ڈیجیٹل لاجک", "Core CS", "terminal", 0.25f, 0.28f, "course_s8_sem12"),
                Landmark("s8_lm2", "Algorithms Arena (Sem 3-4)", "ڈیٹا اسٹرکچرز و الگورتھم", "DSA", "network", 0.75f, 0.28f, "course_s8_sem34"),
                Landmark("s8_lm3", "AI & Cloud Nexus (Sem 5-6)", "مصنوعی ذہانت و ایس کیو ایل", "AI/ML", "brain", 0.25f, 0.72f, "course_s8_sem56"),
                Landmark("s8_lm4", "FYP Capstone Citadel (Sem 7-8)", "فائنل پروجیکٹ مرکز", "Capstone", "shield", 0.75f, 0.72f, "course_s8_sem78")
            )
        )
    )

    val allCourses: List<Course> = listOf(
        // =========================================================
        // STAGE 1: PREP / KG (AGES 4-5)
        // =========================================================
        Course(
            id = "course_s1_eng",
            stageId = 1,
            title = "Complete ABC Alphabet World",
            urduTitle = "مکمل انگریزی حروف A-B-C-D",
            subject = "English",
            iconKey = "abc",
            units = listOf(
                CourseUnit(
                    id = "u_s1_e1",
                    courseId = "course_s1_eng",
                    title = "Unit 1: Letters A, B, C, D (Tracing & Phonics)",
                    urduTitle = "یونٹ ۱: حروف A، B، C، D (ٹریسنگ اور صوتیات)",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_e1_a",
                            unitId = "u_s1_e1",
                            title = "Letter A: Apple & Ant (Tracing)",
                            urduTitle = "حرف A: ٹریسنگ اور الفاظ",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 1,
                            targetCharacter = "A",
                            storyPages = listOf(
                                StoryPage(1, "A is for Apple! Crisp, sweet and red.", "A سے Apple (سیب)! میٹھا اور لال۔", "apple"),
                                StoryPage(2, "Follow the guide to trace capital letter A!", "بڑے حرف A کو ٹریس کرنے کے لیے انگلی سے نشانات ملائیں!", "trace_a")
                            )
                        ),
                        Lesson(
                            id = "les_s1_e1_b",
                            unitId = "u_s1_e1",
                            title = "Letter B: Ball & Bird (Tracing)",
                            urduTitle = "حرف B: گیند اور چڑیا (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 2,
                            targetCharacter = "B",
                            storyPages = listOf(
                                StoryPage(1, "B is for Ball! Bounce it high up to the sky.", "B سے Ball (گیند)! اونچا اچھالیں۔", "ball"),
                                StoryPage(2, "Trace down and draw two curves for B!", "حرف B کو احتیاط سے ٹریس کریں!", "trace_b")
                            )
                        ),
                        Lesson(
                            id = "les_s1_e1_c",
                            unitId = "u_s1_e1",
                            title = "Letter C: Cat & Car (Tracing)",
                            urduTitle = "حرف C: بلی اور کار (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 3,
                            targetCharacter = "C",
                            storyPages = listOf(
                                StoryPage(1, "C is for Cat! Soft and playful kitty.", "C سے Cat (بلی)! پیاری اور دوستانہ۔", "cat"),
                                StoryPage(2, "Draw one big curve to trace letter C!", "ایک گول دائرہ نما موڑ بنا کر C ٹریس کریں!", "trace_c")
                            )
                        ),
                        Lesson(
                            id = "les_s1_e1_d",
                            unitId = "u_s1_e1",
                            title = "Letter D: Dog & Duck (Tracing)",
                            urduTitle = "حرف D: کتا اور بطخ (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 4,
                            targetCharacter = "D",
                            storyPages = listOf(
                                StoryPage(1, "D is for Dog! Loyal and happy friend.", "D سے Dog (کتا)! وفادار دوست۔", "dog"),
                                StoryPage(2, "Draw a straight line down and a big round tummy for D!", "سیدھی لکیر اور بڑا گول موڑ بنا کر D ٹریس کریں!", "trace_d")
                            )
                        ),
                        Lesson(
                            id = "les_s1_e1_match",
                            unitId = "u_s1_e1",
                            title = "A-B-C-D Picture Card Matching",
                            urduTitle = "حروف اور تصاویر کا ملاپ کھیل",
                            type = LessonType.MINIGAME_MATCHING,
                            xpReward = 40,
                            coinsReward = 15,
                            order = 5,
                            matchingPairs = listOf(
                                MatchingPair("m1", "A", "🍎 Apple"),
                                MatchingPair("m2", "B", "⚽ Ball"),
                                MatchingPair("m3", "C", "🐱 Cat"),
                                MatchingPair("m4", "D", "🐶 Dog")
                            )
                        ),
                        Lesson(
                            id = "les_s1_e1_quiz",
                            unitId = "u_s1_e1",
                            title = "A-B-C-D Phonics Mastery Quiz",
                            urduTitle = "حروف A-B-C-D کوئز امتحان",
                            type = LessonType.QUIZ,
                            xpReward = 50,
                            coinsReward = 20,
                            order = 6,
                            questions = listOf(
                                QuizQuestion(
                                    id = "q_abcd_1",
                                    question = "What letter does 'Apple' begin with?",
                                    urduQuestion = "لفظ 'Apple' کس حرف سے شروع ہوتا ہے؟",
                                    options = listOf("Letter A", "Letter B", "Letter C", "Letter D"),
                                    urduOptions = listOf("حرف A", "حرف B", "حرف C", "حرف D"),
                                    correctIndex = 0,
                                    explanation = "Apple starts with letter A!",
                                    urduExplanation = "Apple حرف A سے شروع ہوتا ہے!"
                                ),
                                QuizQuestion(
                                    id = "q_abcd_2",
                                    question = "Which animal starts with the letter C?",
                                    urduQuestion = "کون سا جانور حرف C سے شروع ہوتا ہے؟",
                                    options = listOf("Elephant", "Dog", "Cat", "Fish"),
                                    urduOptions = listOf("ہاتھی (Elephant)", "کتا (Dog)", "بلی (Cat)", "مچھلی (Fish)"),
                                    correctIndex = 2,
                                    explanation = "C is for Cat!",
                                    urduExplanation = "C سے Cat بنتا ہے!"
                                ),
                                QuizQuestion(
                                    id = "q_abcd_3",
                                    question = "What letter comes after C in the alphabet?",
                                    urduQuestion = "انگریزی حروف میں C کے بعد کون سا حرف آتا ہے؟",
                                    options = listOf("A", "B", "D", "E"),
                                    urduOptions = listOf("A", "B", "D", "E"),
                                    correctIndex = 2,
                                    explanation = "A, B, C, D... D follows C!",
                                    urduExplanation = "A، B، C، D... C کے بعد D آتا ہے!"
                                )
                            )
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_e2",
                    courseId = "course_s1_eng",
                    title = "Unit 2: Letters E, F, G, H",
                    urduTitle = "یونٹ ۲: حروف E، F، G، H",
                    order = 2,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_e2_e",
                            unitId = "u_s1_e2",
                            title = "Letter E: Elephant & Egg",
                            urduTitle = "حرف E: ہاتھی اور انڈا",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 1,
                            targetCharacter = "E"
                        ),
                        Lesson(
                            id = "les_s1_e2_f",
                            unitId = "u_s1_e2",
                            title = "Letter F: Fish & Frog",
                            urduTitle = "حرف F: مچھلی اور مینڈک",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 2,
                            targetCharacter = "F"
                        ),
                        Lesson(
                            id = "les_s1_e2_g",
                            unitId = "u_s1_e2",
                            title = "Letter G: Grapes & Giraffe",
                            urduTitle = "حرف G: انگور اور زرافہ",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 3,
                            targetCharacter = "G"
                        ),
                        Lesson(
                            id = "les_s1_e2_h",
                            unitId = "u_s1_e2",
                            title = "Letter H: Hat & House",
                            urduTitle = "حرف H: ٹوپی اور گھر",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 4,
                            targetCharacter = "H"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_e3",
                    courseId = "course_s1_eng",
                    title = "Unit 3: Letters I, J, K, L",
                    urduTitle = "یونٹ ۳: حروف I، J، K، L",
                    order = 3,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_e3_i",
                            unitId = "u_s1_e3",
                            title = "Letter I: Ice Cream & Island",
                            urduTitle = "حرف I: آئس کریم اور جزیرہ",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 1,
                            targetCharacter = "I"
                        ),
                        Lesson(
                            id = "les_s1_e3_j",
                            unitId = "u_s1_e3",
                            title = "Letter J: Juice & Jellyfish",
                            urduTitle = "حرف J: جوس اور جیلی فش",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 2,
                            targetCharacter = "J"
                        ),
                        Lesson(
                            id = "les_s1_e3_k",
                            unitId = "u_s1_e3",
                            title = "Letter K: Kite & King",
                            urduTitle = "حرف K: پتنگ اور بادشاہ",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 3,
                            targetCharacter = "K"
                        ),
                        Lesson(
                            id = "les_s1_e3_l",
                            unitId = "u_s1_e3",
                            title = "Letter L: Lion & Lemon",
                            urduTitle = "حرف L: شیر اور لیمو",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 4,
                            targetCharacter = "L"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_e4",
                    courseId = "course_s1_eng",
                    title = "Unit 4: Letters M, N, O, P",
                    urduTitle = "یونٹ ۴: حروف M، N، O، P",
                    order = 4,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_e4_m",
                            unitId = "u_s1_e4",
                            title = "Letter M: Monkey & Mango",
                            urduTitle = "حرف M: بندر اور آم",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 1,
                            targetCharacter = "M"
                        ),
                        Lesson(
                            id = "les_s1_e4_n",
                            unitId = "u_s1_e4",
                            title = "Letter N: Nest & Nose",
                            urduTitle = "حرف N: گھونسلا اور ناک",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 2,
                            targetCharacter = "N"
                        ),
                        Lesson(
                            id = "les_s1_e4_o",
                            unitId = "u_s1_e4",
                            title = "Letter O: Owl & Orange",
                            urduTitle = "حرف O: الو اور مالٹا",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 3,
                            targetCharacter = "O"
                        ),
                        Lesson(
                            id = "les_s1_e4_p",
                            unitId = "u_s1_e4",
                            title = "Letter P: Penguin & Pizza",
                            urduTitle = "حرف P: پینگوئن اور پیزا",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 4,
                            targetCharacter = "P"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_e5",
                    courseId = "course_s1_eng",
                    title = "Unit 5: Letters Q, R, S, T",
                    urduTitle = "یونٹ ۵: حروف Q، R، S، T",
                    order = 5,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_e5_q",
                            unitId = "u_s1_e5",
                            title = "Letter Q: Queen & Quill",
                            urduTitle = "حرف Q: ملکہ اور پر",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 1,
                            targetCharacter = "Q"
                        ),
                        Lesson(
                            id = "les_s1_e5_r",
                            unitId = "u_s1_e5",
                            title = "Letter R: Rocket & Rainbow",
                            urduTitle = "حرف R: راکٹ اور قوس قزح",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 2,
                            targetCharacter = "R"
                        ),
                        Lesson(
                            id = "les_s1_e5_s",
                            unitId = "u_s1_e5",
                            title = "Letter S: Sun & Star",
                            urduTitle = "حرف S: سورج اور ستارہ",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 3,
                            targetCharacter = "S"
                        ),
                        Lesson(
                            id = "les_s1_e5_t",
                            unitId = "u_s1_e5",
                            title = "Letter T: Tiger & Tree",
                            urduTitle = "حرف T: شیر اور درخت",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 4,
                            targetCharacter = "T"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_e6",
                    courseId = "course_s1_eng",
                    title = "Unit 6: Letters U, V, W, X, Y, Z",
                    urduTitle = "یونٹ ۶: حروف U، V، W، X، Y، Z",
                    order = 6,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_e6_u",
                            unitId = "u_s1_e6",
                            title = "Letter U: Umbrella & Up",
                            urduTitle = "حرف U: چھتری اور اوپر",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 1,
                            targetCharacter = "U"
                        ),
                        Lesson(
                            id = "les_s1_e6_v",
                            unitId = "u_s1_e6",
                            title = "Letter V: Van & Violin",
                            urduTitle = "حرف V: وین اور وائلن",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 2,
                            targetCharacter = "V"
                        ),
                        Lesson(
                            id = "les_s1_e6_w",
                            unitId = "u_s1_e6",
                            title = "Letter W: Watermelon & Watch",
                            urduTitle = "حرف W: تربوز اور گھڑی",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 35,
                            coinsReward = 12,
                            order = 3,
                            targetCharacter = "W"
                        ),
                        Lesson(
                            id = "les_s1_e6_xyz",
                            unitId = "u_s1_e6",
                            title = "Letters X, Y, Z: Xylophone, Yarn, Zebra",
                            urduTitle = "حروف X، Y، Z: زائیلوفون، دھاگہ، زیبرا",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 40,
                            coinsReward = 15,
                            order = 4,
                            targetCharacter = "Z"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_e7",
                    courseId = "course_s1_eng",
                    title = "Unit 7: Complete A-Z All 26 Letters Mega Workshop",
                    urduTitle = "یونٹ ۷: مکمل تمام ۲۶ حروفِ تہجی میگا ورکشاپ",
                    order = 7,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_e7_mega",
                            unitId = "u_s1_e7",
                            title = "Complete Alphabet Master Class (A to Z)",
                            urduTitle = "مکمل A-Z حروف کی جامع کلاس (صوتیات، ٹریسنگ، الفاظ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 100,
                            coinsReward = 50,
                            order = 1,
                            targetCharacter = "A"
                        )
                    )
                )
            )
        ),

        // STAGE 1: MATH (NUMBERS 1-100 & SHAPES)
        Course(
            id = "course_s1_math",
            stageId = 1,
            title = "Numbers 1-100 & Shapes Workshop",
            urduTitle = "گنتی ۱ سے ۱۰۰ اور اشکال ورکشاپ",
            subject = "Math",
            iconKey = "numbers",
            units = listOf(
                CourseUnit(
                    id = "u_s1_m1",
                    courseId = "course_s1_math",
                    title = "Unit 1: Counting 1 to 10 with Object Counter",
                    urduTitle = "یونٹ ۱: ۱ سے ۱۰ تک اشیاء کی گنتی",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_m1_1",
                            unitId = "u_s1_m1",
                            title = "Count Objects & Numbers (Interactive)",
                            urduTitle = "اشیاء و اعداد کی انٹرایکٹو گنتی",
                            type = LessonType.SIMULATION,
                            xpReward = 35,
                            coinsReward = 15,
                            order = 1,
                            simulationType = "COUNTING_LAB",
                            simulationCount = 10
                        ),
                        Lesson(
                            id = "les_s1_m1_trace1",
                            unitId = "u_s1_m1",
                            title = "Trace Number 1 (One Star)",
                            urduTitle = "نمبر ۱ ٹریس کریں (ایک ستارہ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 2,
                            targetCharacter = "1"
                        ),
                        Lesson(
                            id = "les_s1_m1_trace2",
                            unitId = "u_s1_m1",
                            title = "Trace Number 2 (Two Swans)",
                            urduTitle = "نمبر ۲ ٹریس کریں",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 3,
                            targetCharacter = "2"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_m2",
                    courseId = "course_s1_math",
                    title = "Unit 2: Counting 11 to 20 & Number Match",
                    urduTitle = "یونٹ ۲: ۱۱ سے ۲۰ تک گنتی اور ملاپ",
                    order = 2,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_m2_count10",
                            unitId = "u_s1_m2",
                            title = "Count 10 to 20 Objects (Interactive)",
                            urduTitle = "۱۰ سے ۲۰ اشیاء انٹرایکٹو گنیں",
                            type = LessonType.SIMULATION,
                            xpReward = 45,
                            coinsReward = 20,
                            order = 1,
                            simulationType = "COUNTING_LAB",
                            simulationCount = 20
                        ),
                        Lesson(
                            id = "les_s1_m2_match",
                            unitId = "u_s1_m2",
                            title = "Number & Objects Matching Game",
                            urduTitle = "اعداد اور اشیاء کا ملاپ",
                            type = LessonType.MINIGAME_MATCHING,
                            xpReward = 40,
                            coinsReward = 15,
                            order = 2,
                            matchingPairs = listOf(
                                MatchingPair("num1", "1", "🌟 One Star"),
                                MatchingPair("num2", "2", "🍎🍎 Two Apples"),
                                MatchingPair("num3", "3", "🐱🐱🐱 Three Cats"),
                                MatchingPair("num4", "4", "🚗🚗🚗🚗 Four Cars")
                            )
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_m3",
                    courseId = "course_s1_math",
                    title = "Unit 3: Basic Shapes & Geometry Sandbox",
                    urduTitle = "یونٹ ۳: بنیادی اشکال اور جیومیٹری",
                    order = 3,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_m3_shapes",
                            unitId = "u_s1_m3",
                            title = "Discover Circle, Square & Triangle",
                            urduTitle = "دائرہ، مربع اور مثلث دریافت کریں",
                            type = LessonType.SIMULATION,
                            xpReward = 40,
                            coinsReward = 20,
                            order = 1,
                            simulationType = "SHAPES_LAB"
                        )
                    )
                )
            )
        ),

        // STAGE 1: URDU (حروفِ تہجی)
        Course(
            id = "course_s1_urdu",
            stageId = 1,
            title = "Urdu Haroof-e-Tahajji Bay",
            urduTitle = "اردو حروفِ تہجی (ا، ب، پ، ت)",
            subject = "Urdu",
            iconKey = "urdu_book",
            units = listOf(
                CourseUnit(
                    id = "u_s1_u1",
                    courseId = "course_s1_urdu",
                    title = "Unit 1: Haroof Alif, Bay, Pay, Tay",
                    urduTitle = "یونٹ ۱: حروف ا، ب، پ، ت",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_u1_alif",
                            unitId = "u_s1_u1",
                            title = "Alif se Anaar (ا سے انار)",
                            urduTitle = "ا سے انار (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 1,
                            targetCharacter = "ا"
                        ),
                        Lesson(
                            id = "les_s1_u1_bay",
                            unitId = "u_s1_u1",
                            title = "Bay se Billi (ب سے بلی)",
                            urduTitle = "ب سے بلی (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 2,
                            targetCharacter = "ب"
                        ),
                        Lesson(
                            id = "les_s1_u1_match",
                            unitId = "u_s1_u1",
                            title = "Urdu Haroof Matching Game",
                            urduTitle = "اردو حروف و تصاویر کا ملاپ",
                            type = LessonType.MINIGAME_MATCHING,
                            xpReward = 35,
                            coinsReward = 15,
                            order = 3,
                            matchingPairs = listOf(
                                MatchingPair("u1", "ا", "🍎 انار (Pomegranate)"),
                                MatchingPair("u2", "ب", "🐱 بلی (Cat)"),
                                MatchingPair("u3", "پ", "🪁 پتنگ (Kite)"),
                                MatchingPair("u4", "ت", "🦋 تتلی (Butterfly)")
                            )
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_u2",
                    courseId = "course_s1_urdu",
                    title = "Unit 2: Haroof Ttay, Say, Jeem, Chay",
                    urduTitle = "یونٹ ۲: حروف ٹ، ث، ج، چ",
                    order = 2,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_u2_ttay",
                            unitId = "u_s1_u2",
                            title = "Ttay se Tamatar (ٹ سے ٹماٹر)",
                            urduTitle = "ٹ سے ٹماٹر (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 1,
                            targetCharacter = "ٹ"
                        ),
                        Lesson(
                            id = "les_s1_u2_jeem",
                            unitId = "u_s1_u2",
                            title = "Jeem se Joota (ج سے جوتا)",
                            urduTitle = "ج سے جوتا (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 2,
                            targetCharacter = "ج"
                        ),
                        Lesson(
                            id = "les_s1_u2_chay",
                            unitId = "u_s1_u2",
                            title = "Chay se Chabi (چ سے چابی)",
                            urduTitle = "چ سے چابی (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 3,
                            targetCharacter = "چ"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_u3",
                    courseId = "course_s1_urdu",
                    title = "Unit 3: Haroof Daal, Ray, Zay (د، ر، ز)",
                    urduTitle = "یونٹ ۳: حروف د، ر، ز",
                    order = 3,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_u3_daal",
                            unitId = "u_s1_u3",
                            title = "Daal se Darakht (د سے درخت)",
                            urduTitle = "د سے درخت (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 1,
                            targetCharacter = "د"
                        ),
                        Lesson(
                            id = "les_s1_u3_ray",
                            unitId = "u_s1_u3",
                            title = "Ray se Rail Gari (ر سے ریل گاڑی)",
                            urduTitle = "ر سے ریل گاڑی (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 2,
                            targetCharacter = "ر"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_u4",
                    courseId = "course_s1_urdu",
                    title = "Unit 4: Haroof Seen, Sheen, Kaaf, Gaaf",
                    urduTitle = "یونٹ ۴: حروف س، ش، ک، گ",
                    order = 4,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_u4_seen",
                            unitId = "u_s1_u4",
                            title = "Seen se Saib (س سے سیب)",
                            urduTitle = "س سے سیب (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 1,
                            targetCharacter = "س"
                        ),
                        Lesson(
                            id = "les_s1_u4_kaaf",
                            unitId = "u_s1_u4",
                            title = "Kaaf se Kitaab (ک سے کتاب)",
                            urduTitle = "ک سے کتاب (ٹریسنگ)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 30,
                            coinsReward = 10,
                            order = 2,
                            targetCharacter = "ک"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s1_u5",
                    courseId = "course_s1_urdu",
                    title = "Unit 5: Complete All 37 Haroof Mega Workshop",
                    urduTitle = "یونٹ ۵: مکمل حروفِ تہجی میگا ماسٹری ورکشاپ (ا تا ے)",
                    order = 5,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_u5_mega",
                            unitId = "u_s1_u5",
                            title = "Complete Urdu Haroof Master Class",
                            urduTitle = "مکمل حروفِ تہجی ورکشاپ (صوتیات، ٹریسنگ، تصاویر)",
                            type = LessonType.MINIGAME_TRACING,
                            xpReward = 100,
                            coinsReward = 50,
                            order = 1,
                            targetCharacter = "ا"
                        )
                    )
                )
            )
        ),

        // STAGE 1: MANNERS & ISLAMIC VALUES
        Course(
            id = "course_s1_manners",
            stageId = 1,
            title = "Good Habits & Golden Manners",
            urduTitle = "پیارے آداب و اچھی عادات",
            subject = "Islamic Studies",
            iconKey = "manners_heart",
            units = listOf(
                CourseUnit(
                    id = "u_s1_man1",
                    courseId = "course_s1_manners",
                    title = "Unit 1: Saying Salam & Bismillah",
                    urduTitle = "یونٹ ۱: سلام اور بسم اللہ کی برکت",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s1_man_salam",
                            unitId = "u_s1_man1",
                            title = "The Power of As-Salamu Alaykum",
                            urduTitle = "السلام علیکم کہنا اور محبت پھیلانا",
                            type = LessonType.STORY,
                            xpReward = 30,
                            coinsReward = 15,
                            order = 1,
                            storyPages = listOf(
                                StoryPage(1, "When you meet friends and family, say 'As-Salamu Alaykum' with a bright smile!", "جب بھی گھر والوں یا دوستوں سے ملیں، مسکرا کر 'السلام علیکم' کہیں!", "salam"),
                                StoryPage(2, "Salam spreads peace, kindness, and smiles everywhere!", "سلام کرنے سے محبت اور سلامتی پھیلتی ہے!", "peace")
                            )
                        ),
                        Lesson(
                            id = "les_s1_man_quiz",
                            unitId = "u_s1_man1",
                            title = "Kindness & Manners Quiz",
                            urduTitle = "آداب و اخلاق کوئز",
                            type = LessonType.QUIZ,
                            xpReward = 35,
                            coinsReward = 15,
                            order = 2,
                            questions = listOf(
                                QuizQuestion(
                                    id = "q_man_1",
                                    question = "What should we say before eating food?",
                                    urduQuestion = "کھانا کھانے سے پہلے کیا پڑھنا چاہیے؟",
                                    options = listOf("Bismillah (بسم اللہ)", "Alhamdulillah", "Good night"),
                                    urduOptions = listOf("بسم اللہ", "الحمد للہ", "شب بخیر"),
                                    correctIndex = 0,
                                    explanation = "We say Bismillah to begin with Allah's blessing!",
                                    urduExplanation = "ہم ہر اچھے کام سے پہلے بسم اللہ پڑھتے ہیں!"
                                )
                            )
                        )
                    )
                )
            )
        ),

        // =========================================================
        // STAGE 2: CLASS 1-2 (AGES 6-7)
        // =========================================================
        Course(
            id = "course_s2_math",
            stageId = 2,
            title = "Addition, Subtraction & Time River",
            urduTitle = "جمع، تفریق اور وقت کا دریا",
            subject = "Math",
            iconKey = "math_add",
            units = listOf(
                CourseUnit(
                    id = "u_s2_m1",
                    courseId = "course_s2_math",
                    title = "Unit 1: Fast Addition & Raft Balancing",
                    urduTitle = "یونٹ ۱: تیز جمع اور کشتی کا توازن",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s2_m1_1",
                            unitId = "u_s2_m1",
                            title = "Jungle Raft Addition Quest (Sums to 20)",
                            urduTitle = "جنگل کشتی جمع مقابلہ",
                            type = LessonType.QUIZ,
                            xpReward = 40,
                            coinsReward = 20,
                            order = 1,
                            questions = listOf(
                                QuizQuestion(
                                    id = "q_s2_1",
                                    question = "What is 7 + 8?",
                                    urduQuestion = "۷ + ۸ کا جواب کیا ہے؟",
                                    options = listOf("14", "15", "16", "17"),
                                    urduOptions = listOf("۱۴", "۱۵", "۱۶", "۱۷"),
                                    correctIndex = 1,
                                    explanation = "7 + 8 = 15. Great job!",
                                    urduExplanation = "۷ + ۸ = ۱۵۔ بہت خوب!"
                                ),
                                QuizQuestion(
                                    id = "q_s2_2",
                                    question = "If you have 12 coconuts and give 4 away, how many are left?",
                                    urduQuestion = "اگر آپ کے پاس ۱۲ ناریل ہوں اور ۴ دے دیں، تو کتنے بچے؟",
                                    options = listOf("6", "7", "8", "9"),
                                    urduOptions = listOf("۶", "۷", "۸", "۹"),
                                    correctIndex = 2,
                                    explanation = "12 - 4 = 8 coconuts remain!",
                                    urduExplanation = "۱۲ - ۴ = ۸ ناریل باقی بچے!"
                                )
                            )
                        ),
                        Lesson(
                            id = "les_s2_m1_clock",
                            unitId = "u_s2_m1",
                            title = "Reading the Clock Time (O'Clock)",
                            urduTitle = "گھڑی کا وقت دیکھنا",
                            type = LessonType.QUIZ,
                            xpReward = 45,
                            coinsReward = 20,
                            order = 2,
                            questions = listOf(
                                QuizQuestion(
                                    id = "q_clock_1",
                                    question = "When the short hand points to 3 and the long hand to 12, what time is it?",
                                    urduQuestion = "جب چھوٹی سوئی ۳ پر اور بڑی سوئی ۱۲ پر ہو تو کیا وقت ہوتا ہے؟",
                                    options = listOf("3:00 O'Clock", "12:00 O'Clock", "3:30"),
                                    urduOptions = listOf("۳ بجے (3:00)", "۱۲ بجے (12:00)", "ساڑھے ۳"),
                                    correctIndex = 0,
                                    explanation = "The short hand shows the hour: 3 O'Clock!",
                                    urduExplanation = "چھوٹی سوئی گھنٹہ بتاتی ہے، یعنی ۳ بجے ہیں!"
                                )
                            )
                        )
                    )
                )
            )
        ),
        Course(
            id = "course_s2_tech",
            stageId = 2,
            title = "Introduction to Computers & Safe Tech",
            urduTitle = "کمپیوٹر تعارف و محفوظ انٹرنیٹ",
            subject = "Computers",
            iconKey = "computer",
            units = listOf(
                CourseUnit(
                    id = "u_s2_t1",
                    courseId = "course_s2_tech",
                    title = "Unit 1: The Computer Machine & Parts",
                    urduTitle = "یونٹ ۱: کمپیوٹر کے بنیادی حصے",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s2_t1_parts",
                            unitId = "u_s2_t1",
                            title = "Computer Hardware Matching",
                            urduTitle = "کمپیوٹر کے پرزوں کا ملاپ",
                            type = LessonType.MINIGAME_MATCHING,
                            xpReward = 40,
                            coinsReward = 20,
                            order = 1,
                            matchingPairs = listOf(
                                MatchingPair("t1", "🖱️ Mouse", "Pointers & Clicks"),
                                MatchingPair("t2", "⌨️ Keyboard", "Typing Letters"),
                                MatchingPair("t3", "🖥️ Monitor", "Screen Display"),
                                MatchingPair("t4", "🧠 CPU", "Computer Brain")
                            )
                        )
                    )
                )
            )
        ),

        // =========================================================
        // STAGE 3: CLASS 3-4 (AGES 8-9)
        // =========================================================
        Course(
            id = "course_s3_math",
            stageId = 3,
            title = "Multiplication Mill & Fractions",
            urduTitle = "ضرب کی چکی اور کسر (Fractions)",
            subject = "Math",
            iconKey = "multiplication",
            units = listOf(
                CourseUnit(
                    id = "u_s3_m1",
                    courseId = "course_s3_math",
                    title = "Unit 1: Times Tables Mastery (6x, 7x, 8x)",
                    urduTitle = "یونٹ ۱: پہاڑے اور ضرب",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s3_m1_tables",
                            unitId = "u_s3_m1",
                            title = "Lightning Multiplication Challenge",
                            urduTitle = "تیز ترین ضرب چیلنج",
                            type = LessonType.QUIZ,
                            xpReward = 50,
                            coinsReward = 25,
                            order = 1,
                            questions = listOf(
                                QuizQuestion(
                                    id = "q_s3_1",
                                    question = "What is 6 × 8?",
                                    urduQuestion = "۶ × ۸ کا جواب کیا ہے؟",
                                    options = listOf("42", "48", "54", "56"),
                                    urduOptions = listOf("۴۲", "۴۸", "۵۴", "۵۶"),
                                    correctIndex = 1,
                                    explanation = "6 times 8 equals 48.",
                                    urduExplanation = "۶ کو ۸ سے ضرب دیں تو ۴۸ بنتا ہے۔"
                                ),
                                QuizQuestion(
                                    id = "q_s3_2",
                                    question = "If an apple pie is cut into 4 equal slices, and you eat 1 slice, what fraction did you eat?",
                                    urduQuestion = "اگر ایک کیک کے ۴ برابر ٹکڑے ہوں اور ۱ کھا لیں، تو کسر کیا ہوگی؟",
                                    options = listOf("1/4", "1/2", "3/4", "4/1"),
                                    urduOptions = listOf("۱/۴", "۱/۲", "۳/۴", "۴/۱"),
                                    correctIndex = 0,
                                    explanation = "1 slice out of 4 total is 1/4 (one quarter).",
                                    urduExplanation = "۴ میں سے ۱ حصہ یعنی ایک چوتھائی (۱/۴)!"
                                )
                            )
                        )
                    )
                )
            )
        ),

        // =========================================================
        // STAGE 5: CLASS 7-8 (AGES 12-13)
        // =========================================================
        Course(
            id = "course_s5_python",
            stageId = 5,
            title = "Python Coding & Logic Arena",
            urduTitle = "پائیتھن کوڈنگ اور منطق",
            subject = "Computer Science",
            iconKey = "python",
            units = listOf(
                CourseUnit(
                    id = "u_s5_p1",
                    courseId = "course_s5_python",
                    title = "Unit 1: Variables, Output & Math",
                    urduTitle = "یونٹ ۱: متغیرات، آؤٹ پٹ اور ریاضی",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s5_p1_1",
                            unitId = "u_s5_p1",
                            title = "Write Your First Python Script (print)",
                            urduTitle = "پہلا پائیتھن اسکرپٹ لکھیں",
                            type = LessonType.CODE_LAB,
                            xpReward = 50,
                            coinsReward = 25,
                            order = 1,
                            codeChallenge = CodeLabChallenge(
                                id = "ch_py_hello",
                                title = "Say Hello to QuestLearn",
                                urduTitle = "کوسٹ لرن کو ہیلو کہو",
                                language = "python",
                                instructions = "Print 'Hello QuestLearn!' using Python's print() function.",
                                initialCode = "# Type your code below\nprint('Hello QuestLearn!')",
                                expectedOutput = "Hello QuestLearn!",
                                solutionCode = "print('Hello QuestLearn!')",
                                hint = "Use print('Hello QuestLearn!') exactly."
                            )
                        ),
                        Lesson(
                            id = "les_s5_p1_vars",
                            unitId = "u_s5_p1",
                            title = "Variables & Arithmetic in Python",
                            urduTitle = "ویری ایبلز اور حسابی عمل",
                            type = LessonType.CODE_LAB,
                            xpReward = 55,
                            coinsReward = 30,
                            order = 2,
                            codeChallenge = CodeLabChallenge(
                                id = "ch_py_math",
                                title = "Calculate Rectangle Area",
                                urduTitle = "مستطیل کا رقبہ نکالیں",
                                language = "python",
                                instructions = "Multiply width = 5 and height = 10, then print the result.",
                                initialCode = "width = 5\nheight = 10\narea = width * height\nprint(area)",
                                expectedOutput = "50",
                                solutionCode = "print(50)",
                                hint = "width * height gives 50."
                            )
                        )
                    )
                )
            )
        ),

        // =========================================================
        // STAGE 6: MATRIC CLASS 9-10 (AGES 14-15)
        // =========================================================
        Course(
            id = "course_s6_math",
            stageId = 6,
            title = "Matric Mathematics: Quadratic Equations & Matrices",
            urduTitle = "میٹرک ریاضی: کواڈریٹک مساوات و میٹرکس",
            subject = "Math",
            iconKey = "matrix",
            units = listOf(
                CourseUnit(
                    id = "u_s6_m1",
                    courseId = "course_s6_math",
                    title = "Unit 1: Quadratic Formula & Discriminant",
                    urduTitle = "یونٹ ۱: دو درجی فارمولا",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s6_m1_quad",
                            unitId = "u_s6_m1",
                            title = "Quadratic Equation Mastery",
                            urduTitle = "دو درجی مساوات کا حل",
                            type = LessonType.QUIZ,
                            xpReward = 60,
                            coinsReward = 30,
                            order = 1,
                            questions = listOf(
                                QuizQuestion(
                                    id = "q_quad_1",
                                    question = "What is the standard form of a quadratic equation?",
                                    urduQuestion = "دو درجی مساوات کی معیاری شکل کیا ہے؟",
                                    options = listOf("ax² + bx + c = 0", "ax + b = 0", "ax³ + bx = c"),
                                    urduOptions = listOf("ax² + bx + c = 0", "ax + b = 0", "ax³ + bx = c"),
                                    correctIndex = 0,
                                    explanation = "Standard form is ax² + bx + c = 0 where a ≠ 0.",
                                    urduExplanation = "معیاری شکل ax² + bx + c = 0 ہے۔"
                                )
                            )
                        )
                    )
                )
            )
        ),

        // =========================================================
        // STAGE 7: INTERMEDIATE ICS (AGES 16-17)
        // =========================================================
        Course(
            id = "course_s7_ics",
            stageId = 7,
            title = "Intermediate ICS: C++ & Object-Oriented Logic",
            urduTitle = "انٹرمیڈیٹ ICS: سی پلس پلس اور او او پی",
            subject = "ICS",
            iconKey = "cpp_gear",
            units = listOf(
                CourseUnit(
                    id = "u_s7_c1",
                    courseId = "course_s7_ics",
                    title = "Unit 1: Classes, Objects & Methods in C++",
                    urduTitle = "یونٹ ۱: کلاسز اور آبجیکٹس",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s7_cpp_class",
                            unitId = "u_s7_c1",
                            title = "Define a C++ Student Class",
                            urduTitle = "C++ میں طالب علم کی کلاس بنائیں",
                            type = LessonType.CODE_LAB,
                            xpReward = 65,
                            coinsReward = 35,
                            order = 1,
                            codeChallenge = CodeLabChallenge(
                                id = "ch_cpp_class",
                                title = "Class Instantiation in C++",
                                urduTitle = "کلاس آبجیکٹ بنانا",
                                language = "cpp",
                                instructions = "Instantiate Student s; and call s.greet(); which prints 'Hello from Student'.",
                                initialCode = "#include <iostream>\nusing namespace std;\n\nclass Student {\npublic:\n    void greet() { cout << \"Hello from Student\"; }\n};\n\nint main() {\n    Student s;\n    s.greet();\n    return 0;\n}",
                                expectedOutput = "Hello from Student",
                                solutionCode = "cout << \"Hello from Student\";",
                                hint = "Create Student s and call s.greet();"
                            )
                        )
                    )
                )
            )
        ),

        // =========================================================
        // STAGE 8: BSCS (AGES 18-22+) - FULL UNIVERSITY LAB
        // =========================================================
        Course(
            id = "course_s8_sem12",
            stageId = 8,
            title = "Semester 1-2: Programming & Digital Logic",
            urduTitle = "سمسٹر ۱-۲: پروگرامنگ اور ڈیجیٹل لاجک",
            subject = "Core CS",
            iconKey = "code_terminal",
            units = listOf(
                CourseUnit(
                    id = "u_s8_c1",
                    courseId = "course_s8_sem12",
                    title = "Unit 1: Digital Logic Gates Simulator",
                    urduTitle = "یونٹ ۱: ڈیجیٹل لاجک گیٹس سمیلیٹر",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s8_dld_1",
                            unitId = "u_s8_c1",
                            title = "Interactive AND, OR, XOR Gate Sandbox",
                            urduTitle = "انٹرایکٹو لاجک گیٹ سمولیشن",
                            type = LessonType.SIMULATION,
                            xpReward = 60,
                            coinsReward = 30,
                            order = 1,
                            simulationType = "LOGIC_GATES_LAB"
                        )
                    )
                ),
                CourseUnit(
                    id = "u_s8_c2",
                    courseId = "course_s8_sem12",
                    title = "Unit 2: Programming Fundamentals (Code Lab)",
                    urduTitle = "یونٹ ۲: پروگرامنگ بنیادی اصول",
                    order = 2,
                    lessons = listOf(
                        Lesson(
                            id = "les_s8_pf_1",
                            unitId = "u_s8_c2",
                            title = "Sum of First N Integers (Algorithm)",
                            urduTitle = "پہلے N اعداد کا مجموعہ",
                            type = LessonType.CODE_LAB,
                            xpReward = 75,
                            coinsReward = 40,
                            order = 1,
                            codeChallenge = CodeLabChallenge(
                                id = "ch_s8_sum",
                                title = "Sum of First N Integers",
                                urduTitle = "پہلے N اعداد کا مجموعہ",
                                language = "python",
                                instructions = "Calculate the sum of first n integers (n=5 -> 15).",
                                initialCode = "def sum_n(n):\n    return n * (n + 1) // 2\n\nprint(sum_n(5))",
                                expectedOutput = "15",
                                solutionCode = "print(15)",
                                hint = "n * (n + 1) // 2 gives 15 for n=5."
                            )
                        )
                    )
                )
            )
        ),
        Course(
            id = "course_s8_sem34",
            stageId = 8,
            title = "Semester 3-4: Data Structures & Algorithms",
            urduTitle = "سمسٹر ۳-۴: ڈیٹا اسٹرکچرز و الگورتھم",
            subject = "DSA",
            iconKey = "tree_graph",
            units = listOf(
                CourseUnit(
                    id = "u_s8_dsa1",
                    courseId = "course_s8_sem34",
                    title = "Unit 1: Binary Search Tree (BST) Visualizer",
                    urduTitle = "یونٹ ۱: بائنری سرچ ٹری کا بصری مظاہرہ",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s8_bst_1",
                            unitId = "u_s8_dsa1",
                            title = "Interactive Binary Search Tree (BST) Lab",
                            urduTitle = "انٹرایکٹو بائنری ٹری نوڈس",
                            type = LessonType.SIMULATION,
                            xpReward = 70,
                            coinsReward = 35,
                            order = 1,
                            simulationType = "BINARY_TREE_LAB"
                        ),
                        Lesson(
                            id = "les_s8_algo_sort",
                            unitId = "u_s8_dsa1",
                            title = "Sorting Algorithms Visualizer (Bubble & Merge)",
                            urduTitle = "سورٹنگ الگورتھم بصری سمیلیٹر",
                            type = LessonType.SIMULATION,
                            xpReward = 70,
                            coinsReward = 35,
                            order = 2,
                            simulationType = "SORTING_LAB"
                        ),
                        Lesson(
                            id = "les_s8_algo_1",
                            unitId = "u_s8_dsa1",
                            title = "Time Complexity Analysis Challenge",
                            urduTitle = "ٹائم کمپلیکسٹی تجزیہ",
                            type = LessonType.QUIZ,
                            xpReward = 65,
                            coinsReward = 30,
                            order = 3,
                            questions = listOf(
                                QuizQuestion(
                                    id = "q_s8_dsa_1",
                                    question = "What is the average time complexity of searching in a balanced BST with N nodes?",
                                    urduQuestion = "متوازن BST میں N نوڈز تلاش کرنے کی اوسط ٹائم کمپلیکسٹی کیا ہے؟",
                                    options = listOf("O(1)", "O(log N)", "O(N)", "O(N log N)"),
                                    urduOptions = listOf("O(1)", "O(log N)", "O(N)", "O(N log N)"),
                                    correctIndex = 1,
                                    explanation = "A balanced BST cuts search space in half at each step: O(log N).",
                                    urduExplanation = "متوازن درخت میں ہر قدم پر تلاش کا دائرہ آدھا ہو جاتا ہے: O(log N)۔"
                                )
                            )
                        )
                    )
                )
            )
        ),
        Course(
            id = "course_s8_sem56",
            stageId = 8,
            title = "Semester 5-6: AI, Machine Learning & Databases",
            urduTitle = "سمسٹر ۵-۶: مصنوعی ذہانت اور ڈیٹا بیس",
            subject = "AI/ML",
            iconKey = "brain",
            units = listOf(
                CourseUnit(
                    id = "u_s8_ai1",
                    courseId = "course_s8_sem56",
                    title = "Unit 1: Neural Networks & Database Joins",
                    urduTitle = "یونٹ ۱: نیورل نیٹ ورک و SQL جوائنز",
                    order = 1,
                    lessons = listOf(
                        Lesson(
                            id = "les_s8_sql_join",
                            unitId = "u_s8_ai1",
                            title = "SQL Relational Query Sandbox",
                            urduTitle = "ایس کیو ایل کوئری چیلنج",
                            type = LessonType.CODE_LAB,
                            xpReward = 80,
                            coinsReward = 45,
                            order = 1,
                            codeChallenge = CodeLabChallenge(
                                id = "ch_s8_sql",
                                title = "SELECT with WHERE Clause",
                                urduTitle = "ڈیٹا بیس سے ریکارڈز تلاش کریں",
                                language = "sql",
                                instructions = "Write a query to retrieve all active students: SELECT * FROM students WHERE status = 'active';",
                                initialCode = "SELECT * FROM students WHERE status = 'active';",
                                expectedOutput = "Retrieved 10 active student rows.",
                                solutionCode = "SELECT * FROM students WHERE status = 'active';",
                                hint = "Use SELECT * FROM students WHERE status = 'active';"
                            )
                        )
                    )
                )
            )
        )
    )

    val sampleAvatars: List<Avatar> = listOf(
        Avatar("avatar_kid_explorer", "Pip the Explorer", "prep", "Playful jungle outfit with binoculars", isUnlocked = true, 0),
        Avatar("avatar_junior_scout", "Aria the Scout", "primary", "Adventure backpack and compass badge", isUnlocked = true, 0),
        Avatar("avatar_cyber_coder", "Cipher Cadet", "middle", "Holo-visor and cyber hoodie", isUnlocked = false, 50),
        Avatar("avatar_scholar", "Dr. Athena", "matric", "Academic crest with glowing tablet", isUnlocked = false, 80),
        Avatar("avatar_tech_architect", "Nexus Engineer", "bscs", "Full cybernetic suit with IDE gauntlet", isUnlocked = false, 120)
    )

    val sampleBadges: List<AchievementBadge> = listOf(
        AchievementBadge("badge_first_step", "First Quest Complete", "پہلا قدم", "Completed your very first learning quest", "⭐", isUnlocked = true, 1f),
        AchievementBadge("badge_streak_3", "Streak Master", "مسلسل ۳ دن", "Maintained a 3-day active learning streak", "🔥", isUnlocked = true, 1f),
        AchievementBadge("badge_alphabet_king", "Alphabet Champion", "حروف کے چیمپئن", "Mastered full A, B, C, D letter tracing", "🔤", isUnlocked = false, 0f),
        AchievementBadge("badge_code_init", "Hello World!", "ہیلو ورلڈ", "Executed first piece of code in Code Lab", "💻", isUnlocked = false, 0f),
        AchievementBadge("badge_logic_guru", "Digital Architect", "ڈیجیٹل ماہر", "Solved Digital Logic Gates simulation", "⚡", isUnlocked = false, 0f),
        AchievementBadge("badge_boss_slayer", "Assessment Master", "امتحانی ماہر", "Passed Stage Milestone Assessment Challenge", "🎓", isUnlocked = false, 0f),
        AchievementBadge("badge_stage_complete", "Stage Champion", "مرحلہ فاتح", "Finished all core units of your academic stage", "🏆", isUnlocked = false, 0.4f)
    )
}

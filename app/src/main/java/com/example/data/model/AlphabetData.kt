package com.example.data.model

data class LetterExample(
    val emoji: String,
    val word: String,
    val urduWord: String
)

data class AlphabetLetterInfo(
    val letter: String,
    val lowercase: String,
    val phonics: String,
    val urduPhonics: String,
    val examples: List<LetterExample>
)

object AlphabetData {
    val englishAlphabet: List<AlphabetLetterInfo> = listOf(
        AlphabetLetterInfo("A", "a", "/æ/ as in Apple", "آواز: اَ (جیسے ایپل)", listOf(
            LetterExample("🍎", "Apple", "سیب"),
            LetterExample("🐜", "Ant", "چیونٹی"),
            LetterExample("✈️", "Airplane", "ہوائی جہاز"),
            LetterExample("🥑", "Avocado", "ایووکاڈو")
        )),
        AlphabetLetterInfo("B", "b", "/b/ as in Ball", "آواز: بَ (جیسے بال)", listOf(
            LetterExample("⚽", "Ball", "گیند"),
            LetterExample("🐻", "Bear", "ریچھ"),
            LetterExample("🍌", "Banana", "کیلا"),
            LetterExample("⛵", "Boat", "کشتی")
        )),
        AlphabetLetterInfo("C", "c", "/k/ as in Cat", "آواز: کَ (جیسے کیٹ)", listOf(
            LetterExample("🐱", "Cat", "بلی"),
            LetterExample("🚗", "Car", "کار"),
            LetterExample("🧁", "Cupcake", "کیک"),
            LetterExample("🥕", "Carrot", "گاجر")
        )),
        AlphabetLetterInfo("D", "d", "/d/ as in Dog", "آواز: ڈَ (جیسے ڈاگ)", listOf(
            LetterExample("🐶", "Dog", "کتا"),
            LetterExample("🦆", "Duck", "بطخ"),
            LetterExample("🥁", "Drum", "ڈھول"),
            LetterExample("🚪", "Door", "دروازہ")
        )),
        AlphabetLetterInfo("E", "e", "/e/ as in Elephant", "آواز: اِ (جیسے ایلیفینٹ)", listOf(
            LetterExample("🐘", "Elephant", "ہاتھی"),
            LetterExample("🥚", "Egg", "انڈا"),
            LetterExample("🦅", "Eagle", "عقاب"),
            LetterExample("🌍", "Earth", "زمین")
        )),
        AlphabetLetterInfo("F", "f", "/f/ as in Fish", "آواز: فَ (جیسے فش)", listOf(
            LetterExample("🐟", "Fish", "مچھلی"),
            LetterExample("🐸", "Frog", "مینڈک"),
            LetterExample("🌺", "Flower", "پھول"),
            LetterExample("🦊", "Fox", "لومڑی")
        )),
        AlphabetLetterInfo("G", "g", "/ɡ/ as in Grapes", "آواز: گَ (جیسے گریپس)", listOf(
            LetterExample("🍇", "Grapes", "انگور"),
            LetterExample("🦒", "Giraffe", "زرافہ"),
            LetterExample("🎸", "Guitar", "گٹار"),
            LetterExample("🐐", "Goat", "بکری")
        )),
        AlphabetLetterInfo("H", "h", "/h/ as in Hat", "آواز: ہَ (جیسے ہیٹ)", listOf(
            LetterExample("🎩", "Hat", "ٹوپی"),
            LetterExample("🏠", "House", "گھر"),
            LetterExample("🐴", "Horse", "گھوڑا"),
            LetterExample("❤️", "Heart", "دل")
        )),
        AlphabetLetterInfo("I", "i", "/ɪ/ as in Ice Cream", "آواز: آئی (جیسے آئس کریم)", listOf(
            LetterExample("🍦", "Ice Cream", "آئس کریم"),
            LetterExample("🏝️", "Island", "جزیرہ"),
            LetterExample("🦎", "Iguana", "چھپکلی"),
            LetterExample("🧊", "Ice", "برف")
        )),
        AlphabetLetterInfo("J", "j", "/dʒ/ as in Juice", "آواز: جَ (جیسے جوس)", listOf(
            LetterExample("🧃", "Juice", "جوس"),
            LetterExample("🪼", "Jellyfish", "جیلی فش"),
            LetterExample("✈️", "Jet", "طیارہ"),
            LetterExample("🏺", "Jar", "مرتبان")
        )),
        AlphabetLetterInfo("K", "k", "/k/ as in Kite", "آواز: کَ (جیسے کائٹ)", listOf(
            LetterExample("🪁", "Kite", "پتنگ"),
            LetterExample("👑", "King", "بادشاہ"),
            LetterExample("🦘", "Kangaroo", "کینگرو"),
            LetterExample("🔑", "Key", "چابی")
        )),
        AlphabetLetterInfo("L", "l", "/l/ as in Lion", "آواز: لَ (جیسے لائن)", listOf(
            LetterExample("🦁", "Lion", "شیر"),
            LetterExample("🍋", "Lemon", "لیمو"),
            LetterExample("🍃", "Leaf", "پتا"),
            LetterExample("💡", "Lamp", "چراغ")
        )),
        AlphabetLetterInfo("M", "m", "/m/ as in Monkey", "آواز: مَ (جیسے منکی)", listOf(
            LetterExample("🐒", "Monkey", "بندر"),
            LetterExample("🌙", "Moon", "چاند"),
            LetterExample("🥭", "Mango", "آم"),
            LetterExample("🥛", "Milk", "دودھ")
        )),
        AlphabetLetterInfo("N", "n", "/n/ as in Nest", "آواز: نَ (جیسے نیسٹ)", listOf(
            LetterExample("🪹", "Nest", "گھونسلا"),
            LetterExample("👃", "Nose", "ناک"),
            LetterExample("🌰", "Nut", "اخروٹ"),
            LetterExample("📝", "Notebook", "کاپی")
        )),
        AlphabetLetterInfo("O", "o", "/ɒ/ as in Owl", "آواز: او (جیسے اول)", listOf(
            LetterExample("🦉", "Owl", "الو"),
            LetterExample("🍊", "Orange", "مالٹا"),
            LetterExample("🐙", "Octopus", "آکٹوپس"),
            LetterExample("🧅", "Onion", "پیاز")
        )),
        AlphabetLetterInfo("P", "p", "/p/ as in Penguin", "آواز: پَ (جیسے پینگوئن)", listOf(
            LetterExample("🐧", "Penguin", "پینگوئن"),
            LetterExample("🍕", "Pizza", "پیزا"),
            LetterExample("🖊️", "Pen", "قلم"),
            LetterExample("🐼", "Panda", "پانڈا")
        )),
        AlphabetLetterInfo("Q", "q", "/kw/ as in Queen", "آواز: کوَ (جیسے کوئین)", listOf(
            LetterExample("👑", "Queen", "ملکہ"),
            LetterExample("❓", "Question", "سوال"),
            LetterExample("🪶", "Quill", "پر"),
            LetterExample("🤫", "Quiet", "خاموش")
        )),
        AlphabetLetterInfo("R", "r", "/r/ as in Rocket", "آواز: رَ (جیسے راکٹ)", listOf(
            LetterExample("🚀", "Rocket", "راکٹ"),
            LetterExample("🌈", "Rainbow", "قوس قزح"),
            LetterExample("🌹", "Rose", "گلاب"),
            LetterExample("🐇", "Rabbit", "خرگوش")
        )),
        AlphabetLetterInfo("S", "s", "/s/ as in Sun", "آواز: سَ (جیسے سن)", listOf(
            LetterExample("☀️", "Sun", "سورج"),
            LetterExample("⭐", "Star", "ستارہ"),
            LetterExample("🍓", "Strawberry", "سٹرابیری"),
            LetterExample("🐍", "Snake", "سانپ")
        )),
        AlphabetLetterInfo("T", "t", "/t/ as in Tiger", "آواز: ٹَ (جیسے ٹائیگر)", listOf(
            LetterExample("🐯", "Tiger", "شیر"),
            LetterExample("🌳", "Tree", "درخت"),
            LetterExample("🚂", "Train", "ریل گاڑی"),
            LetterExample("🍅", "Tomato", "ٹماٹر")
        )),
        AlphabetLetterInfo("U", "u", "/ʌ/ as in Umbrella", "آواز: اَ (جیسے امبریلا)", listOf(
            LetterExample("☂️", "Umbrella", "چھتری"),
            LetterExample("🦄", "Unicorn", "یونیکورن"),
            LetterExample("⬆️", "Up", "اوپر"),
            LetterExample("🏺", "Urn", "مرتبان")
        )),
        AlphabetLetterInfo("V", "v", "/v/ as in Van", "آواز: وَ (جیسے وین)", listOf(
            LetterExample("🚐", "Van", "وین"),
            LetterExample("🎻", "Violin", "وائلن"),
            LetterExample("🌋", "Volcano", "آتش فشاں"),
            LetterExample("🥗", "Vegetables", "سبزیاں")
        )),
        AlphabetLetterInfo("W", "w", "/w/ as in Watermelon", "آواز: وا (جیسے واٹرمیلن)", listOf(
            LetterExample("🍉", "Watermelon", "تربوز"),
            LetterExample("⌚", "Watch", "گھڑی"),
            LetterExample("🌊", "Wave", "لہر"),
            LetterExample("🪟", "Window", "کھڑکی")
        )),
        AlphabetLetterInfo("X", "x", "/ks/ as in Xylophone", "آواز: ز / کس", listOf(
            LetterExample("📱", "Xylophone", "زائیلوفون"),
            LetterExample("🩻", "X-ray", "ایکس رے"),
            LetterExample("📦", "Box", "ڈبہ"),
            LetterExample("🦊", "Fox", "لومڑی")
        )),
        AlphabetLetterInfo("Y", "y", "/j/ as in Yarn", "آواز: یَ (جیسے یارن)", listOf(
            LetterExample("🧶", "Yarn", "دھاگہ"),
            LetterExample("🛥️", "Yacht", "کشتی"),
            LetterExample("🟡", "Yellow", "پیلا"),
            LetterExample("🪀", "Yo-yo", "یو یو")
        )),
        AlphabetLetterInfo("Z", "z", "/z/ as in Zebra", "آواز: زَ (جیسے زیبرا)", listOf(
            LetterExample("🦓", "Zebra", "زیبرا"),
            LetterExample("🤐", "Zipper", "زپ"),
            LetterExample("🦁", "Zoo", "چڑیا گھر"),
            LetterExample("0️⃣", "Zero", "صفر")
        ))
    )

    val urduAlphabet: List<AlphabetLetterInfo> = listOf(
        AlphabetLetterInfo("ا", "الف", "Alif", "الف سے انار", listOf(
            LetterExample("🍎", "انار", "Pomegranate"),
            LetterExample("🐪", "اونٹ", "Camel"),
            LetterExample("🍇", "انگور", "Grapes")
        )),
        AlphabetLetterInfo("ب", "بے", "Bay", "بے سے بلی", listOf(
            LetterExample("🐱", "بلی", "Cat"),
            LetterExample("🎒", "بستہ", "School Bag"),
            LetterExample("🦆", "بطخ", "Duck")
        )),
        AlphabetLetterInfo("پ", "پے", "Pay", "پے سے پنکھا", listOf(
            LetterExample("🪭", "پنکھا", "Fan"),
            LetterExample("🌱", "پودا", "Plant"),
            LetterExample("🪁", "پتنگ", "Kite")
        )),
        AlphabetLetterInfo("ت", "تے", "Tay", "تے سے تتلی", listOf(
            LetterExample("🦋", "تتلی", "Butterfly"),
            LetterExample("🔒", "تالا", "Lock"),
            LetterExample("🍉", "تربوز", "Watermelon")
        )),
        AlphabetLetterInfo("ٹ", "ٹے", "Ttav", "ٹے سے ٹماٹر", listOf(
            LetterExample("🍅", "ٹماٹر", "Tomato"),
            LetterExample("🧢", "ٹوپی", "Cap"),
            LetterExample("🧺", "ٹوکری", "Basket")
        )),
        AlphabetLetterInfo("ث", "ثے", "Say", "ثے سے ثمر", listOf(
            LetterExample("🍓", "ثمر (پھل)", "Fruits")
        )),
        AlphabetLetterInfo("ج", "جیم", "Jeem", "جیم سے جہاز", listOf(
            LetterExample("✈️", "جہاز", "Airplane"),
            LetterExample("🫖", "جگ", "Jug"),
            LetterExample("👞", "جوتا", "Shoe")
        )),
        AlphabetLetterInfo("چ", "چے", "Chay", "چے سے چڑیا", listOf(
            LetterExample("🐦", "چڑیا", "Sparrow"),
            LetterExample("🌙", "چاند", "Moon"),
            LetterExample("🗝️", "چابی", "Key")
        )),
        AlphabetLetterInfo("ح", "حے", "Bari Hay", "حے سے حلوہ", listOf(
            LetterExample("🍯", "حلوہ", "Sweet Halwa"),
            LetterExample("🏊", "حوض", "Pond")
        )),
        AlphabetLetterInfo("خ", "خے", "Khay", "خے سے خرگوش", listOf(
            LetterExample("🐇", "خرگوش", "Rabbit"),
            LetterExample("🍈", "خربوزہ", "Melon"),
            LetterExample("✉️", "خط", "Letter")
        )),
        AlphabetLetterInfo("د", "دال", "Daal", "دال سے درخت", listOf(
            LetterExample("🌳", "درخت", "Tree"),
            LetterExample("🚪", "دروازہ", "Door"),
            LetterExample("💊", "دوا", "Medicine")
        )),
        AlphabetLetterInfo("ڈ", "ڈال", "Ddaal", "ڈال سے ڈول", listOf(
            LetterExample("🪣", "ڈول", "Bucket"),
            LetterExample("🩺", "ڈاکٹر", "Doctor"),
            LetterExample("📦", "ڈبہ", "Box")
        )),
        AlphabetLetterInfo("ذ", "ذال", "Zaal", "ذال سے ذخیرہ", listOf(
            LetterExample("🌾", "ذخیرہ", "Granary / Reserve")
        )),
        AlphabetLetterInfo("ر", "رے", "Ray", "رے سے ریل گاڑی", listOf(
            LetterExample("🚂", "ریل گاڑی", "Train"),
            LetterExample("🪢", "رسی", "Rope")
        )),
        AlphabetLetterInfo("ڑ", "ڑے", "Rray", "ڑے سے پہاڑ", listOf(
            LetterExample("⛰️", "پہاڑ", "Mountain"),
            LetterExample("🚗", "گاڑی", "Car")
        )),
        AlphabetLetterInfo("ز", "زے", "Zay", "زے سے زرافہ", listOf(
            LetterExample("🦒", "زرافہ", "Giraffe"),
            LetterExample("⛓️", "زنجیر", "Chain")
        )),
        AlphabetLetterInfo("ژ", "ژے", "Zzay", "ژے سے ژالہ باری", listOf(
            LetterExample("🌧️", "ژالہ باری", "Hailstorm")
        )),
        AlphabetLetterInfo("س", "سین", "Seen", "سین سے سیب", listOf(
            LetterExample("🍎", "سیب", "Apple"),
            LetterExample("☀️", "سورج", "Sun"),
            LetterExample("🚲", "سائیکل", "Bicycle")
        )),
        AlphabetLetterInfo("ش", "شین", "Sheen", "شین سے شیر", listOf(
            LetterExample("🦁", "شیر", "Lion"),
            LetterExample("🕯️", "شمع", "Candle")
        )),
        AlphabetLetterInfo("ص", "صاد", "Suad", "صاد سے صابن", listOf(
            LetterExample("🧼", "صابن", "Soap"),
            LetterExample("🧰", "صندوق", "Chest")
        )),
        AlphabetLetterInfo("ض", "ضاد", "Zuad", "ضاد سے ضعیف", listOf(
            LetterExample("👴", "ضعیف", "Elderly")
        )),
        AlphabetLetterInfo("ط", "طوئے", "Toay", "طوئے سے طوطا", listOf(
            LetterExample("🦜", "طوطا", "Parrot"),
            LetterExample("✈️", "طیارہ", "Plane")
        )),
        AlphabetLetterInfo("ظ", "ظوئے", "Zoay", "ظوئے سے ظروف", listOf(
            LetterExample("🏺", "ظروف (برتن)", "Utensils")
        )),
        AlphabetLetterInfo("ع", "عین", "Ain", "عین سے عقاب", listOf(
            LetterExample("🦅", "عقاب", "Eagle"),
            LetterExample("👓", "عینک", "Glasses")
        )),
        AlphabetLetterInfo("غ", "غین", "Ghain", "غین سے غبارہ", listOf(
            LetterExample("🎈", "غبارہ", "Balloon"),
            LetterExample("🪨", "غار", "Cave")
        )),
        AlphabetLetterInfo("ف", "فے", "Fay", "فے سے فوارہ", listOf(
            LetterExample("⛲", "فوارہ", "Fountain"),
            LetterExample("⚽", "فٹبال", "Football")
        )),
        AlphabetLetterInfo("ق", "قاف", "Qaaf", "قاف سے قلم", listOf(
            LetterExample("✒️", "قلم", "Pen"),
            LetterExample("🔒", "قفل (تالا)", "Lock"),
            LetterExample("📖", "قرآن", "Holy Quran")
        )),
        AlphabetLetterInfo("ک", "کاف", "Kaaf", "کاف سے کتاب", listOf(
            LetterExample("📚", "کتاب", "Book"),
            LetterExample("🕊️", "کبوتر", "Pigeon"),
            LetterExample("🪑", "کرسی", "Chair")
        )),
        AlphabetLetterInfo("گ", "گاف", "Gaaf", "گاف سے گائے", listOf(
            LetterExample("🐄", "گائے", "Cow"),
            LetterExample("⚽", "گیند", "Ball"),
            LetterExample("🪆", "گڑیا", "Doll")
        )),
        AlphabetLetterInfo("ل", "لام", "Laam", "لام سے لومڑی", listOf(
            LetterExample("🦊", "لومڑی", "Fox"),
            LetterExample("🍋", "لیموں", "Lemon")
        )),
        AlphabetLetterInfo("م", "میم", "Meem", "میم سے مور", listOf(
            LetterExample("🦚", "مور", "Peacock"),
            LetterExample("🐟", "مچھلی", "Fish"),
            LetterExample("🕌", "مسجد", "Mosque")
        )),
        AlphabetLetterInfo("ن", "نون", "Noon", "نون سے نل", listOf(
            LetterExample("🚰", "نل", "Tap"),
            LetterExample("🍐", "ناشپاتی", "Pear"),
            LetterExample("👃", "ناک", "Nose")
        )),
        AlphabetLetterInfo("و", "واؤ", "Wao", "واؤ سے ورق", listOf(
            LetterExample("📄", "ورق", "Paper Sheet"),
            LetterExample("🥋", "وردی", "Uniform")
        )),
        AlphabetLetterInfo("ہ", "ہے", "Choti Hay", "ہے سے ہاتھی", listOf(
            LetterExample("🐘", "ہاتھی", "Elephant"),
            LetterExample("✋", "ہاتھ", "Hand"),
            LetterExample("📿", "ہار", "Necklace")
        )),
        AlphabetLetterInfo("ء", "ہمزہ", "Hamza", "ہمزہ", listOf(
            LetterExample("☕", "چائے", "Tea"),
            LetterExample("🐄", "گائے", "Cow")
        )),
        AlphabetLetterInfo("ی", "چھوٹی یے", "Choti Yay", "یے سے یکہ", listOf(
            LetterExample("🛞", "یکہ", "Cart"),
            LetterExample("💎", "یاقوت", "Ruby")
        )),
        AlphabetLetterInfo("ے", "بڑی یے", "Bari Yay", "بڑی یے", listOf(
            LetterExample("👦", "لڑکے", "Boys"),
            LetterExample("🦜", "طوطے", "Parrots")
        ))
    )
}

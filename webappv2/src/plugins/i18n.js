import Vue from 'vue'
import VueI18n from "vue-i18n";

Vue.use(VueI18n)

const msg = {
    ru: {
        signIn: 'Войти',
        signUp: 'Зарегистрироваться',
        language: 'Язык: Русский',
        welcome: 'Добро пожаловать на<br/>\n' +
            '<b>МаксУслуги</b>',
        welcomeSubtitle: 'Новейшая платформа Межпланетной Республики Ингирманландия ' +
            'для ВУЗов и студентов, которая облегчает процесс поступления',
        footerWarning: 'Все события, персонажи и страны вымышлены.<br/>' +
            'Любое сходство с реальными событиями случайно.<br/>© Тростин Максим, 2023 г. ',
        departmentOfEducation: 'Департамент образования межпланетной Ингирманландской республики',
        login: 'Логин',
        password: 'Пароль',
        fullName: 'Полное имя',
        enterLogin: 'Введите логин',
        enterPassword: 'Введите пароль',
        enterFullName: 'Введите Ваше полное имя',
        loginDesc: 'Логин - уникальное имя, используемое для входа в аккаунт',
        passwordRestrictionDesc: 'Ваш пароль должен содержать хотя бы 6 символов',
        fullNameDesc: 'Вводите имя в соответствии с Вашим паспортом (ФИО)',
        signAs: 'Войти как...',
        shortCoincidentalWarning: 'Все лица, события и страны вымышлены',
        student: 'Абитуриент',
        universityRepresentative: 'Представитель ВУЗа',
        next: 'Далее',
        russian: 'Русский язык',
        math: 'Математика',
        ingirmanlandian: 'Ингирманландский',
        english: 'Английский язык',
        IT: 'Информатика',
        physics: 'Физика',
        literature: 'Литература',
        enterExams: 'Введите Ваши результаты экзаменов',
        enterRussian: 'Введите баллы по русскому языку',
        enterMath: 'Введите баллы по математике',
        enterIngirmanlandian: 'Введите баллы по ингирманландскому',
        enterEnglish: 'Введите баллы по английскому',
        enterIT: 'Введите баллы по информатике',
        enterPhysics: 'Введите баллы по физике',
        enterLiterature: 'Введите баллы по литературе',
        finish: 'Завершить',
        universitiesTableTitle: 'Список университетов:',
        ColumnUniversityName: 'Название университета',
        ColumnUniversityPlanet: 'Планета',
        ColumnUniversityCity: 'Город',
        name: 'Название',
        helloUser: 'Здравствуйте,',
        yourExamsUser: 'Ваши результаты экзаменов',
        changeProfileSettings: 'Настройки аккаунта',
        exit: 'Выйти',
        findUniversity: 'Поиск университетов',
        findCourse: 'Поиск курсов',
        pickedCoursesTitle: 'Вы подали заявки на',
        newFullName: 'Новое имя',
        newPassword: 'Новый пароль',
        oldPassword: 'Старый пароль',
        newLogin: 'Новый логин',
        pageReloadToast: 'Обновите страницу',
        pageReloadToastBody: 'К сожалению, при смене языка не все надписи могут измениться. ' +
            'Обновите страницу для полных изменений',
        controlPanelTitle: 'Панель управления курсами',
        prevMinScore: 'Проходной балл',
        budgetPlaces: 'Бюджетные места',
        commercePlaces: 'Контрактные места',
        addCourse: 'Новый курс',
        howToEnterUniversity: 'Как поступить в 2523 году?',
        howToStep1: '1. Сдайте экзамены',
        howToStep2: '2. Зарегистрируйтесь в МаксУслугах',
        howToStep3: '3. Подайте заявки на интересные вам направления',
        howToStep4: '4. Следите за списками',
        howToStep5: '5. Подайте заявление на одно из направлений',
        howToStep6: '6. Обучайтесь в университете мечты',
        save: 'Сохранить',
        cancel: 'Отменить',
        pageNotFound: 'Страница не найдена',
        error: 'Ошибка',
        pageNotFoundDesc: 'Страница, которую вы ищете, не найдена',
        dontHaveAcc: 'Нет аккаунта',
        alreadyHaveAcc: 'Уже есть аккаунт',
        back: 'Назад',
        applyToUn: 'Подать заявление'
    },
    en: {
        signIn: 'Sign In',
        signUp: 'Sign Up',
        language: 'Language: English',
        welcome: 'Welcome to the<br/>\n' +
            '<b>MaxServices</b>',
        welcomeSubtitle: 'Modern platform of Interplanetary Republic of Ingrea ' +
            'for universities and applicants that ease process of admission',
        footerWarning: 'All events, characters and countries are fictitious.<br/>' +
            'Any resemblance to actual events is coincidental.<br/>© Trostin Maksym, 2023',
        departmentOfEducation: 'Department of education of Interplanetary Republic of Ingrea',
        login: 'Login',
        password: 'Password',
        fullName: 'Full Name',
        enterLogin: 'Enter login',
        enterPassword: 'Enter password',
        enterFullName: 'Enter your full name',
        loginDesc: 'Login is a unique name used for logging in account',
        passwordRestrictionDesc: 'Your password must be at least 6 characters long',
        fullNameDesc: 'Enter your full name according to passport',
        signAs: 'Sign In As...',
        shortCoincidentalWarning: 'All events, characters and countries are fictitious.',
        student: 'Enrollee',
        universityRepresentative: 'University Representative',
        next: 'Next',
        russian: 'Russian',
        math: 'Math',
        ingirmanlandian: 'Ingirmanlandian',
        english: 'English',
        IT: 'Computer Science',
        physics: 'Physics',
        literature: 'Literature',
        enterExams: 'Enter your scores for exams',
        enterRussian: 'Enter score for Russian language exam',
        enterMath: 'Enter score for Math exam',
        enterIngirmanlandian: 'Enter score for Ingirmanlandian exam',
        enterEnglish: 'Enter score for English language exam',
        enterIT: 'Enter score for Computer Science exam',
        enterPhysics: 'Enter score for Physics exam',
        enterLiterature: 'Enter score for literature exam',
        finish: 'Finish',
        universitiesTableTitle: 'Universities list:',
        ColumnUniversityName: 'University Name',
        ColumnUniversityPlanet: 'Planet',
        ColumnUniversityCity: 'City',
        name: 'Name',
        helloUser: 'Hello,',
        yourExamsUser: 'Your exam scores',
        changeProfileSettings: 'Account Settings',
        exit: 'Exit',
        findUniversity: 'Find university',
        findCourse: 'Find course',
        pickedCoursesTitle: 'You have applied to',
        newFullName: 'New full name',
        newPassword: 'New password',
        oldPassword: 'Old password',
        newLogin: 'New login',
        pageReloadToast: 'Refresh the page',
        pageReloadToastBody: 'Unfortunately, not all of the text lines can change while switching website language. ' +
            'Please, refresh the page to complete changes',
        controlPanelTitle: 'Course control panel',
        prevMinScore: 'Passing score',
        budgetPlaces: 'Budget places',
        commercePlaces: 'Commerce places',
        addCourse: 'Add new course',
        howToEnterUniversity: 'How to enter university in 2523?',
        howToStep1: '1. Pass exams',
        howToStep2: '2. Sign Up in MaxServices',
        howToStep3: '3. Apply on courses you interested in',
        howToStep4: '4. Keep track on lists',
        howToStep5: '5. Pick one of courses you`ve applied to',
        howToStep6: '6. Study in university of your dream',
        save: 'Save',
        cancel: 'Cancel',
        pageNotFound: 'Page not found',
        error: 'Error',
        pageNotFoundDesc: 'We couldn`t find the page you`re looking for',
        dontHaveAcc: 'Don`t have an account',
        alreadyHaveAcc: 'Already have an account',
        back: 'Return',
        applyToUn: 'Apply',
    },
    pl: {
        signIn: 'Zalogować się',
        signUp: 'Utwórz konto',
        language: 'Język: Polski',
        welcome: 'Witajcie w<br/>\n' +
            '<b>MaksUsługi</b>',
        welcomeSubtitle: 'Najnowsza platforma Międzyplanetarną Republiki Ingrea ' +
            'dla uniwersytetów oraz kandydatów, która ułatwia proces przyjęcia',
        footerWarning: 'Wszystkie wydarzenia, postacie i kraje są fikcyjne.<br/>' +
            'Wszelkie podobieństwo do rzeczywistych wydarzeń jest przypadkowe.<br/>© Maksym Trzciński, 2023 r.',
        departmentOfEducation: 'Wydział edukacji Międzyplanetarną Republiki Ingrea',
        login: 'Login',
        password: 'Hasło',
        fullName: 'Pełne imię i nazwisko',
        enterLogin: 'Wprowadź login',
        enterPassword: 'Wprowadź hasło',
        enterFullName: 'Wprowadź pełne imię i nazwisko',
        loginDesc: 'Login jest unikalnym nazwiskiem używanym dla logowania w konto',
        passwordRestrictionDesc: 'Hasło musi mieć co najmniej 6 znaków',
        fullNameDesc: 'Wprowadź pełne imię i nazwisko zgodnie z paszportem',
        signAs: 'Zalogować się jako...',
        shortCoincidentalWarning: 'Wszystkie wydarzenia, postacie i kraje są fikcyjne',
        student: 'Kandydat',
        universityRepresentative: 'Przedstawiciel Uczelni',
        next: 'Dalej',
        russian: 'Język rosyjski',
        math: 'Matematyka',
        ingirmanlandian: 'Język ingermanlandski',
        english: 'Język angielski',
        IT: 'Informatyka',
        physics: 'Fizyka',
        literature: 'Literatura',
        enterExams: 'Wprowadź oceny za egzaminy',
        enterRussian: 'Wprowadź oceny za język rosyjski',
        enterMath: 'Wprowadź oceny za matematykę',
        enterIngirmanlandian: 'Wprowadź oceny za język ingermanlandski',
        enterEnglish: 'Wprowadź oceny za  język angielski',
        enterIT: 'Wprowadź oceny za informatykę',
        enterPhysics: 'Wprowadź oceny za fizykę',
        enterLiterature: 'Wprowadź oceny za literaturę',
        finish: 'Zakończ',
        universitiesTableTitle: 'Lista uniwersytetów:',
        ColumnUniversityName: 'Nazwa Uniwersytetu',
        ColumnUniversityPlanet: 'Planeta',
        ColumnUniversityCity: 'Miasto',
        name: 'Nazwa',
        helloUser: 'Witajcie,',
        yourExamsUser: 'Twoje oceny za egzaminy',
        changeProfileSettings: 'Ustawienia konta',
        exit: 'Wyjdź',
        findUniversity: 'Szukaj na uniwersytecie',
        findCourse: 'Szukaj na kurs',
        pickedCoursesTitle: 'Złożyłeś wnioski na',
        newFullName: 'Nowe imię i nazwisko',
        newPassword: 'Nowe hasło',
        oldPassword: 'Stare hasło',
        newLogin: 'Nowy login',
        pageReloadToast: 'Odśwież stronę',
        pageReloadToastBody: 'Niestety, nie wszystkie napisy mogą się zmienić przy zmianie języka. ' +
            'Odśwież stronę żeby zakończyć zmianę',
        controlPanelTitle: 'Panel sterowania kursów',
        prevMinScore: 'Wynik zaliczenia',
        budgetPlaces: 'Budżetowe miejsca',
        commercePlaces: 'Płatne miejsca',
        addCourse: 'Nowy kurs',
        howToEnterUniversity: 'Jak dostać się na uniwersytet w 2523 roku?',
        howToStep1: '1. Zdaj egzaminy',
        howToStep2: '2. Zarejestruj się',
        howToStep3: '3. Aplikuj na interesujące cię kursy',
        howToStep4: '4. Postępuj zgodnie z listami',
        howToStep5: '5. Wybierz jeden z kursów, na które aplikowałeś',
        howToStep6: '6. Studiuj na wymarzonej uczelni',
        save: 'Zapisz',
        cancel: 'Anuluj',
        pageNotFound: 'Ta strona nie istnieje',
        error: 'Błąd',
        pageNotFoundDesc: 'Nie możemy znaleźć strony której szukasz',
        dontHaveAcc: 'Nie masz konta',
        alreadyHaveAcc: 'Już masz konto',
        back: 'Powrót',
        applyToUn: 'Aplikować',
    },
    ing: {
        signIn: 'Kirjaudu sisään',
        signUp: 'Kirjaudu',
        language: 'Kieli: Ingirmanland',
        welcome: 'Tervetuloa<br/>\n' +
            '<b>MaxPalvelut</b>',
        welcomeSubtitle: 'Moderni alusta Ingrean planeettojenvälisestä tasavallasta '+
        'yliopistoille ja hakijoille, jotka helpottavat pääsyä',
        footerWarning: 'Kaikki tapahtumat, hahmot ja maat ovat kuvitteellisia.<br/>' +
            'Kaikki samankaltaisuudet todellisiin tapahtumiin on sattumaa.<br/>© Trostin Maksym, 2023',
        departmentOfEducation: 'Ingrean planeettojenvälisen tasavallan koulutuslaitos',
        login: 'Kirjaudu sisään',
        password: 'Salasana',
        fullName: 'Koko nimi',
        enterLogin: 'Syötä sisäänkirjautuminen',
        enterPassword: 'Kirjoita salasana',
        enterFullName: 'Kirjoita koko nimesi',
        loginDesc: 'Login on yksilöllinen nimi, jota käytetään tilille kirjautumiseen',
        passwordRestrictionDesc: 'Salasanasi tulee olla vähintään 6 merkkiä pitkä',
        fullNameDesc: 'Kirjoita koko nimesi passin mukaan',
        signAs: 'Kirjaudu sisään nimellä...',
        shortCoincidentalWarning: 'Kaikki tapahtumat, hahmot ja maat ovat kuvitteellisia.',
        student: 'Ilmoittautunut',
        universityRepresentative: 'Yliopiston edustaja',
        next: 'Seuraava',
        russian: 'Venäjän kieli',
        math: 'Matematiikka',
        ingirmanlandian: 'Ingirmanlandilainen',
        english: 'Englanti',
        IT: 'Tietokone Tiede',
        physics: 'Fysiikka',
        literature: 'Kirjallisuus',
        enterExams: 'Anna kokeen pisteet',
        enterRussian: 'Anna pisteet venäjän kielen kokeesta',
        enterMath: 'Anna matematiikan kokeen pisteet',
        enterIngirmanlandian: 'Anna Ingirmanland-kokeen pisteet',
        enterEnglish: 'Anna pisteet englannin kielen kokeesta',
        enterIT: 'Anna tietojenkäsittelytieteen kokeen pisteet',
        enterPhysics: 'Anna pisteet fysiikan kokeesta',
        enterLiterature: 'Anna kirjallisuuskokeen pisteet',
        finish: 'Suorittaa loppuun',
        universitiesTableTitle: 'Yliopistojen lista:',
        ColumnUniversityName: 'Yliopiston nimi',
        ColumnUniversityPlanet: 'Planeetta',
        ColumnUniversityCity: 'Kaupunki',
        name: 'Nimi',
        helloUser: 'Hei,',
        yourExamsUser: 'Tenttipisteesi',
        changeProfileSettings: 'Tilin asetukset',
        exit: 'Poistu',
        findUniversity: 'Etsi yliopisto',
        findCourse: 'Etsi kurssi',
        pickedCoursesTitle: 'Olet hakenut',
        newFullName: 'Uusi koko nimi',
        newPassword: 'Uusi salasana',
        oldPassword: 'Vanha salasana',
        newLogin: 'Uusi kirjautuminen',
        pageReloadToast: 'Päivitä sivu',
        pageReloadToastBody: 'Valitettavasti kaikki tekstirivit eivät voi muuttua verkkosivuston kieltä vaihdettaessa. ' +
            'Päivitä sivu tehdäksesi muutokset loppuun',
        controlPanelTitle: 'Kurssin ohjauspaneeli',
        prevMinScore: 'Läpäisypisteet',
        budgetPlaces: 'Budjettipaikat',
        commercePlaces: 'Kauppapaikat',
        addCourse: 'Lisää uusi kurssi',
        howToEnterUniversity: 'Kuinka päästä yliopistoon vuonna 2523?',
        howToStep1: '1. Läpäistä kokeet',
        howToStep2: '2. Rekisteröidy MaxPalveluihin',
        howToStep3: '3. Hae sinua kiinnostaville kursseille',
        howToStep4: '4. Seuraa listoja',
        howToStep5: '5. Valitse yksi kursseista, johon olet hakenut',
        howToStep6: '6. Opiskele unelmiesi yliopistossa',
        save: 'Tallentaa',
        cancel: 'Peruuttaa',
        pageNotFound: 'Sivua ei löydetty',
        error: 'Virhe',
        pageNotFoundDesc: 'Emme löytäneet etsimääsi sivua',
        dontHaveAcc: 'Eikö sinulla ole tiliä',
        alreadyHaveAcc: 'Onko sinulla jo tili',
        back: 'Palata',
        applyToUn: 'hakea',
    }
}
let lcl = localStorage.getItem('lang')
if (lcl === null) {
    lcl = 'ru'
}
export const i18n = new VueI18n({
    locale: lcl,
    fallbackLocale: 'ru',
    messages: msg
})
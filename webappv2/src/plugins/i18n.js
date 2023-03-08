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
        finish: 'Завершить'
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
        finish: 'Finish'
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
        finish: 'Zakończ'
    }
}

export const i18n = new VueI18n({
    locale: 'ru',
    fallbackLocale: 'ru',
    messages: msg
})
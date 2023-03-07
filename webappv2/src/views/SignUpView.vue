<template>
  <div class="sign-in">
  <Transition name="fade">
    <div class="form" v-if="step === 1">
      <img :src="require('@/assets/logo-yellow.png')" width="128px" alt="nlk"/>
      <b-alert variant="danger" :show="notFilled">Все поля должны быть заполнены</b-alert>
      <b-form @submit="onSubmit()" class="B-form">
        <b-form-group
            id="input-group-1"
            label="Логин:"
            label-for="input-1"
            description="Логин - уникальное имя, используемое для входа в аккаунт"
        >
          <b-form-input
              id="input-1"
              v-model="form.login"
              placeholder="Введите логин"
          ></b-form-input>
        </b-form-group>

        <b-form-group
            id="input-group-1"
            label="Полное Имя:"
            label-for="input-1"
            description="Вводите имя в соответствии с Вашим паспортом (ФИО)"
        >
          <b-form-input
              id="input-1"
              v-model="form.name"
              placeholder="Введите имя"
          ></b-form-input>
        </b-form-group>

        <b-form-group
            id="input-group-2"
            label="Пароль:"
            label-for="input-2"
            description="Ваш пароль должен содежать хотя бы 6 символов"
        >
          <b-form-input
              id="input-2"
              v-model="form.password"
              type="password"
              placeholder="Введите пароль"
              :state="passwordStrong()"
          ></b-form-input>
        </b-form-group>

        <b-form-checkbox
            id="checkbox-1"
            v-model="form.checked"
            name="checkbox-1"
            value="true"
            unchecked-value="false"
        >
          Все лица, события и страны вымышлены
        </b-form-checkbox>

        <b-button
            @click="onCheck()"
            variant="warning"
            class="B-button"
            :disabled="anyFormEmpty()"
        >Далее <b-icon-arrow-right/></b-button>
      </b-form>
    </div>
  </Transition>
    <Transition name="fade">
      <div class="form" id="2" v-if="step === 2">
        <img :src="require('@/assets/logo-yellow.png')" width="128px" alt="nlk"/>
        <div>Введите Ваши результаты экзаменов:</div>
        <div class="exams">
          <div class="exam">
            <b-form-checkbox
                v-model="exams__passed.russian"
                disabled
            >
              <div class="checkboxName" v-if="!exams__passed.russian">
              Русский
              </div>
            </b-form-checkbox>
            <div class="examInputDiv" v-if="exams__passed.russian">
            <b-form-input v-model="exams.russian" class="examInput"  type="number" min="0" max="100" step="1" placeholder="Русский"/>
            <b-form-text id="input-live-help">Баллы за экзамен по русскому языку</b-form-text>
            </div>
          </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.math"
              disabled
          >
            <div class="checkboxName" v-if="!exams__passed.math">
              Математика
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.math">
            <b-form-input v-model="exams.math" class="examInput"  type="number" min="0" max="100" step="1" placeholder="Математика"/>
            <b-form-text id="input-live-help">Баллы за экзамен по математике</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.ingirmanlandian"
          >
            <div class="checkboxName" v-if="!exams__passed.ingirmanlandian">
              Ингирманландский
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.ingirmanlandian">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.ingirmanlandian" placeholder="Ингирманландский"/>
            <b-form-text id="input-live-help">Баллы за экзамен по ингирманландскому</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.english"
          >
            <div class="checkboxName" v-if="!exams__passed.english">
              Английский
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.english">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.english" placeholder="Англиский"/>
            <b-form-text id="input-live-help">Баллы за экзамен по англискому</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.IT"
          >
            <div class="checkboxName" v-if="!exams__passed.IT">
              Информатика
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.IT">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.IT" placeholder="Информатика"/>
            <b-form-text id="input-live-help">Баллы за экзамен по информатике</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.physics"
          >
            <div class="checkboxName" v-if="!exams__passed.physics">
              Физика
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.physics">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.physics" placeholder="Физика"/>
            <b-form-text id="input-live-help">Баллы за экзамен по физике</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.literature"
          >
            <div class="checkboxName" v-if="!exams__passed.literature">
              Литература
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.literature">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.literature" placeholder="Литература"/>
            <b-form-text id="input-live-help">Баллы за экзамен по литературе</b-form-text>
          </div>
        </div>
        </div>
        <b-button
            type="submit"
            variant="warning"
            class="B-button"
            @click="onSubmit()"
        >Завершить регистрацию</b-button>
      </div>
    </Transition>

  </div>
</template>

<script>
export default {
  name: "SignInView",
  data() {
    return {
      form: {
        login: '',
        name: '',
        password: '',
        checked: 'false',
      },
      roles: [{ text: 'Войти как...', value: null }, 'Студент', 'Представитель ВУЗа'],
      notFilled: false,
      step: 1,
      exams: {
        russian: null,
        math: null,
        ingirmanlandian: null,
        english: null,
        IT: null,
        physics: null,
        literature: null,
      },
      exams__passed: {
        russian: true,
        math: true,
        ingirmanlandian: false,
        english: false,
        IT: false,
        physics: false,
        literature: false,
      }
    }
  },
  methods: {
    passwordStrong() {
      return this.form.password.length >= 6
    },
    anyFormEmpty() {
      return this.form.login === '' || this.form.password === '' ||
          this.form.checked === 'false' || !this.passwordStrong()
    },
    onCheck() {
      event.preventDefault()
      if (!this.anyFormEmpty()) {
        this.notFilled = false
        this.step = 2
      } else {
        this.notFilled = true
      }
    },
    onSubmit() {
      event.preventDefault()
      // TODO: Check on exams filled
      // TODO: Send post request
      if (!this.anyFormEmpty()) {
        this.notFilled = false
        let new_user = {
          name: this.form.name,
          login: this.form.login,
          password: this.form.password,
          exams: [
              this.exams.russian,
              this.exams.math,
              this.exams.ingirmanlandian,
              this.exams.english,
              this.exams.IT,
              this.exams.physics,
              this.exams.literature,
          ]
        }
        for (let i = 0; i < new_user.exams.length; i++) {
          if (new_user.exams[i] == null) {
            new_user.exams[i] = 0;
          }
        }
        console.log(JSON.stringify(new_user))
      } else {
        this.notFilled = true
      }
    }
  }
}
</script>

<style scoped>
.sign-in {
  background-color: #0b0b0c;
  height: calc(100vh - 60px);
  display: flex;
  vertical-align: middle;
  justify-content: center;
  align-items: center;
}
.form {
  background-color: #fefefe;
  padding: 20px 100px 50px 100px;
  border-radius: 30px 10px 30px 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: black;
}
.B-form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  color: black;
}
.B-button {
  margin-top: 20px;
}
.exams {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
.exam {
  display: flex;
  flex-direction: row;
  vertical-align: middle;
  align-content: flex-start;
}
.examInput {
  width: 200px;
  height: 24px;
}
.examInputDiv {
  display: flex;
  flex-direction: column;
}
.fade-enter {
  opacity: 0;
  position: fixed;
}
.fade-enter-to {
  opacity: 1;
  position: fixed;
}
.fade-leave {
  opacity: 1;
  position: fixed;
}
.fade-leave-to {
  opacity: 0;
  position: fixed;
}
.fade-leave-active {
  transition: opacity 1000ms ease;
}
.fade-enter-active {
  transition: opacity 1000ms;
  transition-delay: 1000ms;
}

</style>
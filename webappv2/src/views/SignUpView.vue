<template>
  <div class="sign-in">
  <Transition name="fade">
    <div class="form" v-if="step === 1">
      <img :src="require('@/assets/logo-yellow.png')" width="128px" alt="nlk"/>
      <b-alert variant="danger" :show="loginTaken">{{$t('loginTaken')}}</b-alert>
      <b-form @submit="onSubmit()" class="B-form">
        <b-form-group
            id="input-group-1"
            :label="$t('login')"
            label-for="input-1"
            :description="$t('loginDesc')"
        >
          <b-form-input
              id="input-1"
              v-model="form.login"
              :placeholder="$t('enterLogin')"
              :state="allowedSymbols(form.login)"
          ></b-form-input>
        </b-form-group>

        <b-form-group
            id="input-group-1"
            :label="$t('fullName')"
            label-for="input-1"
            :description="$t('fullNameDesc')"
        >
          <b-form-input
              id="input-1"
              v-model="form.name"
              :placeholder="$t('enterFullName')"
              :state="allowedSymbols(form.name, true)"
          ></b-form-input>
        </b-form-group>

        <b-form-group
            id="input-group-2"
            :label="$t('password')"
            label-for="input-2"
            :description="$t('passwordRestrictionDesc')"
        >
          <b-form-input
              id="input-2"
              v-model="form.password"
              type="password"
              :placeholder="$t('enterPassword')"
              :state="passwordStrong() && allowedSymbols(form.password)"
          ></b-form-input>
        </b-form-group>

        <b-form-checkbox
            id="checkbox-1"
            v-model="form.checked"
            name="checkbox-1"
            value="true"
            unchecked-value="false"
        >
          {{ $t('shortCoincidentalWarning') }}
        </b-form-checkbox>

        <b-button
            @click="onCheck()"
            variant="warning"
            class="B-button"
            :disabled="anyFormEmpty()"
        > {{$t('next')}} <b-icon-arrow-right/></b-button>
        <div class="signInUpSwap">{{$t('alreadyHaveAcc')}}? <router-link to="/signin" class="prettyLink">{{$t('signIn')}}<b-icon-arrow-right/></router-link></div>
      </b-form>
    </div>
  </Transition>
    <Transition name="fade">
      <div class="form" id="2" v-if="step === 2">
        <img :src="require('@/assets/logo-yellow.png')" width="128px" alt="nlk"/>
        <div>{{ $t('enterExams') }}:</div>
        <div class="exams">
          <div class="exam">
            <b-form-checkbox
                v-model="exams__passed.russian"
                disabled
            >
              <div class="checkboxName" v-if="!exams__passed.russian">
              {{ $t('russian') }}
              </div>
            </b-form-checkbox>
            <div class="examInputDiv" v-if="exams__passed.russian">
            <b-form-input v-model="exams.russian" class="examInput"  type="number" min="0" max="100" step="1"
                          :placeholder="$t('russian')"
                          :state="examNormal(this.exams.russian, this.exams__passed.russian)"/>
            <b-form-text id="input-live-help">{{ $t('enterRussian') }}</b-form-text>
            </div>
          </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.math"
              disabled
          >
            <div class="checkboxName" v-if="!exams__passed.math">
              {{ $t('math') }}
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.math">
            <b-form-input v-model="exams.math" class="examInput"  type="number" min="0" max="100" step="1"
                          :placeholder=" $t('math') "
                          :state="examNormal(this.exams.math, this.exams__passed.math)"/>
            <b-form-text id="input-live-help">{{$t('enterMath')}}</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.ingirmanlandian"
          >
            <div class="checkboxName" v-if="!exams__passed.ingirmanlandian">
              {{$t('ingirmanlandian')}}
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.ingirmanlandian">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.ingirmanlandian"
                          :placeholder="$t('ingirmanlandian')"
                          :state="examNormal(this.exams.ingirmanlandian, this.exams__passed.ingirmanlandian)"/>
            <b-form-text id="input-live-help">{{$t('enterIngirmanlandian')}}</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.english"
          >
            <div class="checkboxName" v-if="!exams__passed.english">
              {{$t('english')}}
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.english">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.english" :placeholder="$t('english')"
                          :state="examNormal(this.exams.english, this.exams__passed.english)"/>
            <b-form-text id="input-live-help">{{$t('enterEnglish')}}</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.IT"
          >
            <div class="checkboxName" v-if="!exams__passed.IT">
              {{$t('IT')}}
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.IT">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.IT" :placeholder="$t('IT')"
                          :state="examNormal(this.exams.IT, this.exams__passed.IT)"/>
            <b-form-text id="input-live-help">{{$t('enterIT')}}</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.physics"
          >
            <div class="checkboxName" v-if="!exams__passed.physics">
              {{$t('physics')}}
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.physics">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.physics" :placeholder="$t('physics')"
                          :state="examNormal(this.exams.physics, this.exams__passed.physics)"/>
            <b-form-text id="input-live-help">{{$t('enterPhysics')}}</b-form-text>
          </div>
        </div>
        <div class="exam">
          <b-form-checkbox
              v-model="exams__passed.literature"
          >
            <div class="checkboxName" v-if="!exams__passed.literature">
              {{$t('literature')}}
            </div>
          </b-form-checkbox>
          <div class="examInputDiv" v-if="exams__passed.literature">
            <b-form-input class="examInput"  type="number" min="0" max="100" step="1"
                          v-model="exams.literature" :placeholder="$t('literature')"
                          :state="examNormal(this.exams.literature, this.exams__passed.literature)"/>
            <b-form-text id="input-live-help">{{$t('enterLiterature')}}</b-form-text>
          </div>
        </div>
        </div>
        <b-button
            type="submit"
            variant="warning"
            class="B-button"
            @click="onSubmit()"
            :disabled="!allExamsNormal()"
        >{{ $t('finish') }}</b-button>
      </div>
    </Transition>

  </div>
</template>

<script>
import {url} from "@/main";
import consts from "@/helpers/consts";

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
      loginTaken: false,
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
  beforeMount() {
    if (localStorage.getItem('uid') !== null) {
      this.$router.push('/')
    }
  },
  methods: {
    allowedSymbols(str, spacesAllowed = false) {
      if (spacesAllowed) {
        return /^[a-zA-Z0-9ĄĘŻŹĆŃŚąężźćńśа-яА-Я ]+$/.test(str);
      } else {
        return /^[a-zA-Z0-9]+$/.test(str);
      }
    },
    passwordStrong() {
      return this.form.password.length >= 6
    },

    examNormal(exam, passed) {
      return exam > 0 && exam <= 100 || !passed;
    },
    allExamsNormal() {
      return (this.exams.russian > 0 && this.exams.russian <= 100 || !this.exams__passed.russian) &&
          (this.exams.math > 0 && this.exams.math <= 100 || !this.exams__passed.math) &&
          (this.exams.ingirmanlandian > 0 && this.exams.ingirmanlandian <= 100 || !this.exams__passed.ingirmanlandian)&&
          (this.exams.english > 0 && this.exams.english <= 100 || !this.exams__passed.english)&&
          (this.exams.IT > 0 && this.exams.IT <= 100 || !this.exams__passed.IT)&&
          (this.exams.physics > 0 && this.exams.physics <= 100 || !this.exams__passed.physics) &&
          (this.exams.literature > 0 && this.exams.literature <= 100 || !this.exams__passed.literature)
    },
    anyFormEmpty() {
      return this.form.login === '' || this.form.password === '' || this.form.name === '' ||
          this.form.checked === 'false' || !this.passwordStrong()
    },
    onCheck() {
      event.preventDefault()
      if (!this.anyFormEmpty()) {

        this.$http.get(url + "/api/" + consts.apiV + "/students/check/" + this.form.login).then(
            response=>{
              this.notFilled = false
              this.step = 2
            }, err=> {
              this.loginTaken = true
            }
        )
      } else {
        this.notFilled = true
      }
    },
    onSubmit() {
      event.preventDefault()

      if (this.anyFormEmpty()) {
        this.notFilled = true
        this.step = 1
        return
      }
      if (!this.allExamsNormal()) {
        return
      }

      this.$http.post(url + "/api/" + consts.apiV + "/students",
          {
            id: -1,
            name: this.form.name,
            login: this.form.login,
            password: this.form.password,
            scores: this.prepareScores()
          }
      ).then(
          response => {
            localStorage.setItem('uid', response.data)
            this.$router.push('/')
          }, err => {
            this.loginTaken = true;
            this.step = 1;
          }
      )
    },
    prepareScores() {
      let scores = {
            Russian: 0,
            Math: 0,
            Ingirmanlandian: 0,
            English: 0,
            IT: 0,
            Physics: 0,
            Literature: 0
      }
      if (this.exams.russian !== null)         scores.Russian =         this.exams.russian;
      if (this.exams.math !== null)            scores.Math =            this.exams.math;
      if (this.exams.ingirmanlandian !== null) scores.Ingirmanlandian = this.exams.ingirmanlandian;
      if (this.exams.english !== null)         scores.English =         this.exams.english;
      if (this.exams.IT !== null)              scores.IT =              this.exams.IT;
      if (this.exams.physics !== null)         scores.Physics =         this.exams.physics;
      if (this.exams.literature !== null)      scores.Literature =      this.exams.literature;
      return scores
    }
  }
}
</script>

<style scoped>
.signInUpSwap {
  margin-top: 5px;
  align-self: center;
}
.prettyLink {
  color: #ffc107;
  cursor: pointer;
  text-decoration: none;
  background-image: linear-gradient(#ffc107, #ffc107);
  background-position: 0 100%;
  background-repeat: no-repeat;
  background-size: 0 2px;
  transition: background-size cubic-bezier(0,.5,0,1) 0.3s;
}
.prettyLink:hover,
.prettyLink:focus {
  text-decoration: none;
  background-size: 100% 2px;
}
.sign-in {
  background-image:  url("@/assets/stacked-waves-haikei (1).svg");
  background-repeat: no-repeat;
  background-size: cover;
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
  transition: opacity 500ms ease;
}
.fade-enter-active {
  transition: opacity 500ms;
  transition-delay: 500ms;
}

</style>
<template>
  <div class="sign-in">
  <Transition name="fade">
    <div class="form" v-if="step === 1">
      <img :src="require('@/assets/logo-yellow.png')" width="128px" alt="nlk"/>
      <b-alert variant="danger" :show="notFilled">Все поля должны быть заполнены</b-alert>
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
          {{ $t('shortCoincidentalWarning') }}
        </b-form-checkbox>

        <b-button
            @click="onCheck()"
            variant="warning"
            class="B-button"
            :disabled="anyFormEmpty()"
        > {{$t('next')}} <b-icon-arrow-right/></b-button>
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
                          :placeholder="$t('russian')"/>
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
                          :placeholder=" $t('math') "/>
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
                          v-model="exams.ingirmanlandian" :placeholder="$t('ingirmanlandian')"/>
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
                          v-model="exams.english" :placeholder="$t('english')"/>
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
                          v-model="exams.IT" :placeholder="$t('IT')"/>
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
                          v-model="exams.physics" :placeholder="$t('physics')"/>
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
                          v-model="exams.literature" :placeholder="$t('literature')"/>
            <b-form-text id="input-live-help">{{$t('enterLiterature')}}</b-form-text>
          </div>
        </div>
        </div>
        <b-button
            type="submit"
            variant="warning"
            class="B-button"
            @click="onSubmit()"
        >{{ $t('finish') }}</b-button>
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
  transition: opacity 500ms ease;
}
.fade-enter-active {
  transition: opacity 500ms;
  transition-delay: 500ms;
}

</style>
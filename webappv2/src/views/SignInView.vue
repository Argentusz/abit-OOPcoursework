<template>
<div class="sign-in">

  <div class="form">
    <img :src="require('@/assets/logo-yellow.png')" width="128px" alt="nlk"/>

    <b-alert class="alertWrongAuth" variant="danger" :show="wrongAuth">Неверный логин или пароль</b-alert>
    <b-form @submit="onSubmit()" class="B-form">
      <b-form-group
          id="input-group-1"
          :label="$t('login') + ':'"
          label-for="input-1"
      >
        <b-form-input
            id="input-1"
            v-model="form.login"
            :placeholder="$t('enterLogin')"
        ></b-form-input>
      </b-form-group>

      <b-form-group
          id="input-group-2"
          :label="$t('password') + ':'"
          label-for="input-2"
      >
        <b-form-input
            id="input-2"
            v-model="form.password"
            type="password"
            :placeholder="$t('enterPassword')"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" :label="$t('signAs')" label-for="input-3">
        <b-form-select
            id="input-3"
            v-model="form.role"
            :options="roles"
        ></b-form-select>
      </b-form-group>
      <b-form-checkbox
          id="checkbox-1"
          v-model="form.checked"
          name="checkbox-1"
          value="true"
          unchecked-value="false"
      >
        <div style="font-size: 1rem">{{ $t('shortCoincidentalWarning') }}</div>
      </b-form-checkbox>

      <b-button
          type="button"
          variant="warning"
          class="B-button"
          :disabled="anyFormEmpty()"
          @click="auth()"
      >{{$t('signIn')}}</b-button>

      <div class="signInUpSwap">{{$t('dontHaveAcc')}}? <router-link to="/signup" class="prettyLink">{{$t('signUp')}}<b-icon-arrow-right/></router-link></div>
    </b-form>


  </div>
  <img :src="require('@/assets/layered-waves-haikei (2).svg')" alt="" class="waves-bottom"/>
</div>
</template>

<script>
import {url} from "@/main";
export default {
  name: "SignInView",
  data() {
    return {
      form: {
        login: '',
        password: '',
        checked: 'false',
        role: null,
      },
      roles: [{ text: this.$t('signAs'), value: null },
        {text: this.$t('student'), value: 'Student'},
        {text: this.$t('universityRepresentative'), value: 'University'}],
      wrongAuth: false,
    }
  },
  beforeMount() {
    if (localStorage.getItem('uid') !== null) {
      this.$router.push('/')
    }
    if (localStorage.getItem('uuid') !== null) {
      this.$router.push('/u')
    }
  },
  methods: {
    anyFormEmpty() {
      return this.form.login === '' || this.form.password === '' ||
          this.form.checked === 'false' || this.form.role === null
    },
    onSubmit() {
      event.preventDefault()
      if (!this.anyFormEmpty()) {
        this.wrongAuth = false
        console.log(JSON.stringify(this.form))
      } else {
        this.wrongAuth = true
      }
    },
    auth() {
      console.log('AUTH')
      let rol = 0
      if (this.form.role === "Student") {rol = 1} else if (this.form.role === "University") {rol = 2}
      console.log({login: this.form.login, password: this.form.password, role: rol, name: ""})
      this.$http.patch(url + "/api/v1/auth",
  {login: this.form.login, password: this.form.password, role: rol, name: ""}).then(
        response=>{
          console.log(response)
          const uid = response.data
          const rs = response.status
          console.log(uid)
          console.log(rs)
          this.wrongAuth = false
          if (rol === 1) {
            localStorage.setItem('uid', uid)
            this.$router.push('/')
          } else {
            localStorage.setItem('uuid', uid)
            this.$router.push('/u')
          }
        },
        err=>{
          this.wrongAuth = true
        })


    }
  }
}
</script>

<style scoped>
.alertWrongAuth {
  width: 100%;
  margin-top: 5px;
  margin-bottom: 5px;
  text-align: center;
}
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
  z-index: 2;
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
.waves-bottom {
  position: absolute;
  bottom: -10px;
  width: 100vw;
}
</style>
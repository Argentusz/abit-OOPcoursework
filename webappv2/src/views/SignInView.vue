<template>
<div class="sign-in">

  <div class="form">
    <img :src="require('@/assets/logo-yellow.png')" width="128px" alt="nlk"/>
    <b-alert variant="danger" :show="notFilled">Все поля должны быть заполнены</b-alert>
    <b-form @submit="onSubmit()" class="B-form">
      <b-form-group
          id="input-group-1"
          label="Логин:"
          label-for="input-1"
      >
        <b-form-input
            id="input-1"
            v-model="form.login"
            placeholder="Введите логин"
        ></b-form-input>
      </b-form-group>

      <b-form-group
          id="input-group-2"
          label="Пароль:"
          label-for="input-2"
      >
        <b-form-input
            id="input-2"
            v-model="form.password"
            type="password"
            placeholder="Введите пароль"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Войти как..." label-for="input-3">
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
        Все лица, события и страны вымышлены
      </b-form-checkbox>

      <b-button
          type="submit"
          variant="warning"
          class="B-button"
          :disabled="anyFormEmpty()"
      >Войти</b-button>


    </b-form>


  </div>

</div>
</template>

<script>
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
      roles: [{ text: 'Войти как...', value: null }, 'Студент', 'Представитель ВУЗа'],
      notFilled: false,
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
        this.notFilled = false
        console.log(JSON.stringify(this.form))
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
</style>
<template>
<div class="studentPOV__container">
  <div class="studentPOV__top">
    <div class="studentProfile">
      <div class="studentAvatar">
        <b-avatar class="studentAvatarIcon" variant="warning" :text="makeInitials()"/>
        {{studentData.name}}
      </div>
      <div class="studentExams">
        <div>
        {{$t('yourExamsUser')}}:
        </div>
        <div v-if="studentData.exams[0] !== 0">
          {{$t('russian')}}:{{studentData.exams[0]}}</div>
        <div v-if="studentData.exams[1] !== 0">
          {{$t('math')}}:{{studentData.exams[1]}}</div>
        <div v-if="studentData.exams[2] !== 0">
          {{$t('ingirmanlandian')}}:{{studentData.exams[2]}}</div>
        <div v-if="studentData.exams[3] !== 0">
          {{$t('english')}}:{{studentData.exams[3]}}</div>
        <div v-if="studentData.exams[4] !== 0">
          {{$t('IT')}}:{{studentData.exams[4]}}</div>
        <div v-if="studentData.exams[5] !== 0">
          {{$t('physics')}}:{{studentData.exams[5]}}</div>
        <div v-if="studentData.exams[6] !== 0">
          {{$t('literature')}}:{{studentData.exams[6]}}</div>
      </div>
      <div class="studentSettingsButtons">
        <b-button class="studentSettingsBtn" variant="warning"
                  v-b-toggle.profile-settings>

          <b-icon-gear class="gearIcon"/>  {{ $t('changeProfileSettings')}}
        </b-button>
        <b-button class="exitBtn" variant="danger">
          <b-icon-box-arrow-right class="gearIcon"/>{{$t('exit')}}
        </b-button>
      </div>
    </div>
    <div class="findButtons">
        <b-button variant="warning" class="findBtn">
          <b-icon-search class="gearIcon"/>{{ $t('findUniversity') }}
        </b-button>
        <b-button variant="warning" class="findBtn">
          <b-icon-search class="gearIcon"/>{{ $t('findCourse') }}
        </b-button>
    </div>
  </div>

<!-- Picked Courses table -->
  <div class="pickedCoursesTitle">
    {{$t('pickedCoursesTitle')}}:
  </div>
  <div>
    <vue-good-table
        :columns="columns"
        :rows="rows"
        style-class="vgt-table striped bordered condensed"

    />
  </div>








<!-- Settings sidebar -->
  <b-sidebar id="profile-settings"
             sidebar-class="border-left border-warning"
             bg-variant="dark"
             :title="$t('changeProfileSettings')"
             text-variant="light"
             width="375px"
             right shadow

  >
    <div class="changeLang px-2 py-2">
      <b-dropdown variant="warning" :text="$t('language')" right>
        <b-dropdown-item @click="setLocale('ru')">Русский</b-dropdown-item>
        <b-dropdown-item @click="setLocale('en')">English</b-dropdown-item>
        <b-dropdown-item @click="setLocale('pl')">Polski</b-dropdown-item>
      </b-dropdown>
    </div>
    <div class="change px-2 py-2" id="name">
      {{$t('fullName')}}: {{studentData.name}}
      <b-form-input :placeholder="$t('newFullName')"/>
    </div>
    <b-button variant="success" class="mx-2">Сохранить<b-icon-check/></b-button>
    <div class="change px-2 py-2" id="name">
      {{$t('login')}}: {{studentData.login}}
      <b-form-input :placeholder="$t('newLogin')"/>
    </div>
    <b-button variant="success" class="mx-2">Сохранить<b-icon-check/></b-button>
    <div class="change px-2 py-2" id="password">
      {{$t('password')}}
      <b-form-input :placeholder="$t('oldPassword')"/>
      <b-form-input class="my-3" :placeholder="$t('newPassword')"/>
    </div>
    <b-button variant="success" class="mx-2">Сохранить<b-icon-check/></b-button>
  </b-sidebar>
</div>
</template>

<script>


export default {
  name: "StudentPOView",
  data() {
    return {
      studentData: {
        id: 1,
        login: 'argentusz',
        name: 'Grzegorz Brzęczyszczykiewicz',
        exams: [90, 90, 0, 0, 90, 100, 0],
      },
      columns: [
        {
          field: "name",
          label: this.$t('name')
        },
        {
          field: "uName",
          label: this.$t('ColumnUniversityName')
        },
        {
          field: "planet",
          label: this.$t('ColumnUniversityPlanet')
        },
        {
          field: "city",
          label: this.$t('ColumnUniversityCity')
        },
      ],
      rows: [
        {name: 'ИВТ', uName:'ЛИТИ', planet: 'Земля', city: 'Санкт-Петербург'},
        {name: 'УУУ', uName:'БРУЧ', planet: 'Земля', city: 'Санкт-Петербург'},
        {name: 'ЫЫЫ', uName:'ИЧМО', planet: 'Земля', city: 'Санкт-Петербург'},
        {name: 'ЯЯЯ', uName:'ХИХИ', planet: 'Марс', city: 'Милки-Вэй'},
      ]
    }
  },
  methods: {
    makeInitials() {
      const arrayName = this.studentData.name.split(" ", 2)
      let res = ''
      for (let i = 0; i < arrayName.length; i++) {
        res+=arrayName[i].charAt(0)
      }
      return res;
    },
    setLocale(locale) {
      this.$i18n.locale = locale
      localStorage.setItem('lang', locale)
      this.$bvToast.toast(this.$t('pageReloadToastBody'),
{
          title: this.$t('pageReloadToast'),
          variant: 'warning',
          solid: true,
          toaster: 'b-toaster-bottom-right'
        }
      )
    }
  }
}
</script>

<style scoped>
.studentPOV__container {
  color: #fff;
  background-color: #0b0b0c;
  min-height: calc(100vh - 60px);
  padding: 60px 50px;
}
.studentPOV__top {
  display: flex;
  flex-direction: row;
  padding-bottom: 20px;
  margin-left: -50px;
  margin-right: -50px;
  padding-left: 50px;
  padding-right: 50px;
  border-bottom: #343a40 2px solid;
}
.studentProfile {
  background-color: #343a40;
  border-radius: 25px;
  width: fit-content;
  padding: 10px 20px;
  display: flex;
  flex-direction: row;
}
.studentAvatar {
  margin-right: 30px;
  display:flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.studentAvatarIcon {
  margin-bottom: 10px;
}
.studentExams {
  margin-right: 10px;
}
.studentSettingsButtons {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}
.studentSettingsBtn {
  display: flex;
  margin-bottom: 10px;
}
.gearIcon {
  margin-top: 1px;
  margin-right:2px;
}
.exitBtn {
  color: #000;
  display: flex;
  align-items: center;
  justify-content: center;
}
.findButtons {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  vertical-align: bottom;
  align-items: flex-end;
  padding: 10px 20px;
}
.findBtn {
  margin-right: 10px;
}
.pickedCoursesTitle {
  font-size: 32px;
  margin-top: 30px;
}
</style>
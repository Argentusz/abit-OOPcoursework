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
        <b-button variant="outline-dark" pill class="findBtn" @click="$router.push('/find/university')">
          <b-icon-search class="gearIcon"/>{{ $t('findUniversity') }}
        </b-button>
        <b-button variant="outline-dark" pill class="findBtn" @click="$router.push('/find/course')">
          <b-icon-search class="gearIcon"/>{{ $t('findCourse') }}
        </b-button>
    </div>
  </div>
<div class="studentPOV__bottom">
<!-- Picked Courses table -->
  <div class="custom-shape-divider-top-1678748417">
    <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
      <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" class="shape-fill"></path>
      <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" class="shape-fill"></path>
      <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" class="shape-fill"></path>
    </svg>
  </div>
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
  <div class="custom-shape-divider-bottom-1678748915">
    <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
      <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" class="shape-fill"></path>
      <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" class="shape-fill"></path>
      <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" class="shape-fill"></path>
    </svg>
  </div>
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
        <b-dropdown-item @click="setLocale('ru')">??????????????</b-dropdown-item>
        <b-dropdown-item @click="setLocale('ing')">Ingirmanlandin</b-dropdown-item>
        <b-dropdown-item @click="setLocale('en')">English</b-dropdown-item>
        <b-dropdown-item @click="setLocale('pl')">Polski</b-dropdown-item>

      </b-dropdown>
    </div>
    <div class="change px-2 py-2" id="name">
      {{$t('fullName')}}: {{studentData.name}}
      <b-form-input :placeholder="$t('newFullName')"/>
    </div>
    <b-button variant="success" class="mx-2">{{$t('save')}}<b-icon-check/></b-button>
    <div class="change px-2 py-2" id="name">
      {{$t('login')}}: {{studentData.login}}
      <b-form-input :placeholder="$t('newLogin')"/>
    </div>
    <b-button variant="success" class="mx-2">{{$t('save')}}<b-icon-check/></b-button>
    <div class="change px-2 py-2" id="password">
      {{$t('password')}}
      <b-form-input :placeholder="$t('oldPassword')"/>
      <b-form-input class="my-3" :placeholder="$t('newPassword')"/>
    </div>
    <b-button variant="success" class="mx-2">{{$t('save')}}<b-icon-check/></b-button>
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
        name: 'Grzegorz Brz??czyszczykiewicz',
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
          field: "prevMinScore",
          label: this.$t('prevMinScore')
        },
        {
          field: "budgetPlaces",
          label: this.$t('budgetPlaces')
        },
        {
          field: "commercePlaces",
          label: this.$t('commercePlaces')
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
        {name: '??????', uName:'????????', prevMinScore: 200, budgetPlaces: 50, commercePlaces: 50, planet: '??????????', city: '??????????-??????????????????'},
        {name: '??????', uName:'????????', prevMinScore: 220, budgetPlaces: 19, commercePlaces: 19, planet: '??????????', city: '??????????-??????????????????'},
        {name: '??????', uName:'????????', prevMinScore: 205, budgetPlaces: 10, commercePlaces: 27, planet: '??????????', city: '??????????-??????????????????'},
        {name: '??????', uName:'????????', prevMinScore: 295, budgetPlaces: 2, commercePlaces: 20, planet: '????????', city: '??????????-??????'},
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

}
.studentPOV__top {
  display: flex;
  flex-direction: row;
  height: 220px;
  padding: 60px 50px 20px;
  background-color: #ffc107;
}
.studentPOV__bottom {
  padding-top: 30px;
  padding-left: 50px;
  padding-right: 50px;
  min-height: calc(100vh - 60px - 220px);

}
.custom-shape-divider-top-1678748417 {
  position: absolute;
  top: 280px;
  left: 0;
  width: 100%;
  overflow: hidden;
  line-height: 0;
}

.custom-shape-divider-top-1678748417 svg {
  position: relative;
  display: block;
  width: calc(100% + 1.3px);
  height: 180px;
  transform: rotateY(180deg);
}

.custom-shape-divider-top-1678748417 .shape-fill {
  fill: #FFC107;
}

/*bottom waves*/
.custom-shape-divider-bottom-1678748915 {
  position: absolute;
  bottom: -10px;
  left: 0;
  width: 100%;
  overflow: hidden;
  line-height: 0;
  transform: rotate(180deg);
}

.custom-shape-divider-bottom-1678748915 svg {
  position: relative;
  display: block;
  width: calc(100% + 1.3px);
  height: 150px;
}

.custom-shape-divider-bottom-1678748915 .shape-fill {
  fill: #343a40;
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
  position: relative;
  text-shadow: 2px 2px #343a40;
  z-index: 3;
  margin-bottom: 100px;
}
</style>
<template>
  <div class="universityPOV__container">
    <div class="universityPOV__top">
      <div class="universityProfile">
        <b-avatar class="universityAvatarIcon" variant="primary" :text="universityData.name.charAt(0)"/>
        {{ universityData.name }}
      </div>
      <div class="controlPanelTitle">
        {{ $t('controlPanelTitle') }}
      </div>
    </div>
    <div class="universityPOV__bottom">
      <div class="custom-shape-divider-top-1678748417">
        <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
          <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" class="shape-fill"></path>
          <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" class="shape-fill"></path>
          <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" class="shape-fill"></path>
        </svg>
      </div>
      <div class="newCourseBtn">
        <b-button
            variant="dark"
            v-b-modal.new-course-modal
            style="z-index: 2"
        >
          {{ $t('addCourse') }} <b-icon-plus/>
        </b-button>
      </div>
      <div class="table">
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
      <b-modal id="new-course-modal"
               :title="$t('addCourse')"
               :ok-title="$t('save')"
               ok-variant="warning"
               cancel-variant="danger"
               :cancel-title="$t('cancel')"
      >
        {{$t('name')}}
        <b-form-input class="my-2" :placeholder="$t('name')" type="text"/>
        {{$t('prevMinScore')}}
        <b-form-input class="my-2" :placeholder="$t('prevMinScore')" type="number"/>
        {{$t('budgetPlaces')}}
        <b-form-input class="my-2" :placeholder="$t('budgetPlaces')" type="number"/>
        {{$t('commercePlaces')}}
        <b-form-input class="my-2" :placeholder="$t('commercePlaces')" type="number"/>
        <div class="exams">
          <div class="examsSide">
            <b-checkbox v-model="newCourseData.exams.russian" disabled>{{$t('russian')}}</b-checkbox>
            <b-checkbox v-model="newCourseData.exams.math" disabled>{{$t('math')}}</b-checkbox>
            <b-checkbox v-model="newCourseData.exams.ingirmanlandian">{{$t('ingirmanlandian')}}</b-checkbox>
            <b-checkbox v-model="newCourseData.exams.english">{{$t('english')}}</b-checkbox>
          </div>
          <div class="examsSide">
            <b-checkbox v-model="newCourseData.exams.IT">{{$t('IT')}}</b-checkbox>
            <b-checkbox v-model="newCourseData.exams.physics">{{$t('physics')}}</b-checkbox>
            <b-checkbox v-model="newCourseData.exams.literature">{{$t('literature')}}</b-checkbox>
          </div>
        </div>
      </b-modal>

    </div>
  </div>
</template>

<script>
export default {
  name: "UniversityPOView",
  data() {
    return {
      universityData: {
        id: 1,
        login: "etu@edu.ig",
        name: "????????",
      },
      columns: [
        {
          field: "name",
          label: this.$t('name')
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
        }
      ],
      rows: [
        {name: '??????', prevMinScore: 200, budgetPlaces: 15, commercePlaces: 30},
        {name: '??????', prevMinScore: 212, budgetPlaces: 20, commercePlaces: 40},
        {name: '??????????', prevMinScore: 234, budgetPlaces: 24, commercePlaces: 32},
      ],
      newCourseData: {
        name: null,
        prevMinScore: null,
        budgetPlaces: null,
        commercePlaces: null,
        exams: {
          russian: true,
          math: true,
          ingirmanlandian: false,
          english: false,
          IT: false,
          physics: false,
          literature: false,
        }
      }
    }
  }
}
</script>

<style scoped>
.universityPOV__container {
  height: calc(100vh - 60px);
  color: #fff;
  background-color: #0b0b0c;
}
.universityPOV__top {
  display: flex;
  flex-direction: row;
  padding: 60px 50px 20px;
  border-bottom: #343a40 2px solid;
  background-color: #ffc107;
}
.universityProfile {
  background-color: #343a40;
  border-radius: 25px;
  width: fit-content;
  padding: 10px 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.universityAvatarIcon {
  margin-bottom: 5px;
}
.controlPanelTitle {
  font-size: 64px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  width: 100%;
  text-shadow: 2px 2px #343a40;
}
.universityPOV__bottom {
  padding-top: 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: calc(100vh - 60px - 220px);
}
.table {
  width: calc(100vw - 250px);
}
.newCourseBtn {
  margin-bottom: 20px;
  width: calc(100vw - 250px);
  display: flex;
  flex-direction: row;
  justify-content: flex-end;

}
.exams {
  display: flex;
  flex-direction: row;
}
.examsSide {
  display: flex;
  flex-direction: column;
  margin-right: 20px;
}
.custom-shape-divider-top-1678748417 {
  position: absolute;
  top: 234px;
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
</style>
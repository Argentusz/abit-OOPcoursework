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
      <div class="newCourseBtn">
        <b-button
            variant="warning">
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
        name: "ЛИТИ",
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
        {name: 'ИВТ', prevMinScore: 200, budgetPlaces: 15, commercePlaces: 30},
        {name: 'УУУ', prevMinScore: 212, budgetPlaces: 20, commercePlaces: 40},
        {name: 'ЫГЫГЫ', prevMinScore: 234, budgetPlaces: 24, commercePlaces: 32},
      ]
    }
  }
}
</script>

<style scoped>
.universityPOV__container {
  height: calc(100vh - 60px);
  color: #fff;
  background-color: #0b0b0c;
  padding: 60px 50px;
}
.universityPOV__top {
  display: flex;
  flex-direction: row;
  padding-bottom: 20px;
  margin-left: -50px;
  margin-right: -50px;
  padding-left: 50px;
  padding-right: 50px;
  border-bottom: #343a40 2px solid;
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
}
.universityPOV__bottom {
  margin-top: 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
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
</style>
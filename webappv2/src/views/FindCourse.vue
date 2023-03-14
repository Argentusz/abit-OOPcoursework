<template>
  <div class="findCourse__container">
    <router-link class="backBtn" to="/"><b-icon-arrow-left/> {{$t('back')}}</router-link>
    <div class="title">
      {{$t('findCourse')}}
    </div>
    <div class="table-div">
      <vue-good-table
          :columns="columns"
          :rows="rows"
          style-class="vgt-table striped bordered condensed"
          class="table"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "FindCourse",
  data() {
    return {
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
        {name: 'ИВТ', uName:'ЛИТИ', prevMinScore: 200, budgetPlaces: 50, commercePlaces: 50, planet: 'Земля', city: 'Санкт-Петербург'},
        {name: 'УУУ', uName:'БРУЧ', prevMinScore: 220, budgetPlaces: 19, commercePlaces: 19, planet: 'Земля', city: 'Санкт-Петербург'},
        {name: 'ЫЫЫ', uName:'ИЧМО', prevMinScore: 205, budgetPlaces: 10, commercePlaces: 27, planet: 'Земля', city: 'Санкт-Петербург'},
        {name: 'ЯЯЯ', uName:'ХИХИ', prevMinScore: 295, budgetPlaces: 2, commercePlaces: 20, planet: 'Марс', city: 'Милки-Вэй'},
      ]
    }
  }
}
</script>

<style scoped>
.findCourse__container {
  height: calc(100vh - 60px);
  background: url("@/assets/circle-scatter-haikei (1).svg") repeat-x fixed left top;
  color: #fff;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}
.backBtn {
  width: fit-content;
  margin-left: 15px;
  margin-top: 20px;
  font-size: 24px;
  color: #ffc107;
  cursor: pointer;
  text-decoration: none;
  background-image: linear-gradient(#ffc107, #ffc107);
  background-position: 0 100%;
  background-repeat: no-repeat;
  background-size: 0 2px;
  transition: background-size cubic-bezier(0,.5,0,1) 0.3s;
}
.backBtn:hover,
.backBtn:focus {
  text-decoration: none;
  background-size: 100% 2px;
}
.title {
  text-align: center;
  font-size: 64px;
  text-shadow: 2px 2px #ffc107;
}
.table-div{
  width: 100vw;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.table {
  width: 80vw;
}
</style>
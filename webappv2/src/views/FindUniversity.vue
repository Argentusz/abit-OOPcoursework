<template>
  <div class="findUniversity__container">
    <router-link class="backBtn" to="/"><b-icon-arrow-left/> {{$t('back')}}</router-link>
    <div class="title">
      {{$t('findUniversity')}}
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
  name: "FindUniversity",
  data() {
    return {
      columns: [
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
        {
          uName: 'ЛИТИ',
          planet: 'Земля',
          city: 'Санкт-Петербург'
        },
        {

          uName: 'БРУЧ',
          planet: 'Земля',
          city: 'Санкт-Петербург'
        },
        {
          uName: 'ИЧМО',
          planet: 'Земля',
          city: 'Санкт-Петербург'
        },
        {
          uName: 'ХИХИ',
          planet: 'Марс',
          city: 'Милки-Вэй'
        },
      ]
    }
  }
}
</script>

<style scoped>
.findUniversity__container {
  height: calc(100vh - 60px);
  background: url("@/assets/blob-scene-haikei (2).svg") repeat-x fixed left top;
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
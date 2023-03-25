<template>
  <div class="findUniversity__container">
    <router-link class="backBtn" to="/"><b-icon-arrow-left/> {{$t('back')}}</router-link>
    <div class="title">
      {{$t('findUniversity')}}
    </div>
    <div class="table-div">
      <ag-grid-vue
        style="width: 1000px; height: 60vh;"
        class="ag-theme-balham-dark"
        :columnDefs="columns"
        :rowData="rows"
      >
      </ag-grid-vue>
    </div>
  </div>
</template>

<script>
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-balham.css";
import { AgGridVue } from "ag-grid-vue";
export default {
  name: "FindUniversity",
  data() {
    return {
      columns: null,
      rows: null,
    }
  },
  components: {
    AgGridVue,
  },
  beforeMount() {
    this.columns = [
      {
        field: "uName",
        headerName: this.$t('ColumnUniversityName'),
        width: 550,
        filter: true, floatingFilter: true,
        floatingFilterComponentParams: { suppressFilterButton: true },
      },
      {
        field: "planet",
        headerName: this.$t('ColumnUniversityPlanet'),
        width: 100,
        filter: true, floatingFilter: true,
        floatingFilterComponentParams: { suppressFilterButton: true },
      },
      {
        field: "city",
        headerName: this.$t('ColumnUniversityCity'),
        width: 350,
        filter: true, floatingFilter: true,
        floatingFilterComponentParams: { suppressFilterButton: true },
      },
    ];
    this.rows = [
      { uName: 'ЛИТИ', planet: 'Земля', city: 'Санкт-Петербург' },
      { uName: 'БРУЧ', planet: 'Земля', city: 'Санкт-Петербург'},
      { uName: 'ИЧМО', planet: 'Земля', city: 'Санкт-Петербург'},
      { uName: 'ХИХИ0', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ1', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ2', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ3', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ4', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ5', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ6', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ7', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ8', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ9', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ10', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ11', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ12', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ13', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ14', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ15', planet: 'Марс', city: 'Милки-Вэй'},
      { uName: 'ХИХИ16', planet: 'Марс', city: 'Милки-Вэй'},
    ]
  },
  methods: {

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
  margin-top: 3vh;
}
.ag-theme-balham-dark {
  --ag-odd-row-background-color: #343a40;
  --ag-header-foreground-color: #ffc107;
  --ag-border-color: #ffc107;
}
</style>

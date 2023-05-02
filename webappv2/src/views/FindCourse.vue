<template>
  <div class="findCourse__container">
    <router-link class="backBtn" to="/"><b-icon-arrow-left/> {{$t('back')}}</router-link>
    <div class="title">
      {{$t('findCourse')}}
    </div>
    <div class="table-btns__container">
      <div class="table-buttons">
      <div class="table-btn">
        <b-button class="b-table-btn" variant="warning"><b-icon-arrow-return-left class="mr-2"/>{{$t('applyToUn')}}</b-button>
      </div>
      </div>
    </div>
    <div class="table__container">
      <div class="table-div">
        <ag-grid-vue
            style="width: 100%; height: 50vh;"
            class="ag-theme-balham-dark"
            :columnDefs="columns"
            :rowData="rows"
            :rowSelection="rowSelection"
            @selection-changed="onSelectionChanged()"
            @grid-ready="onGridReady"
        >
        </ag-grid-vue>
      </div>
    </div>
  </div>
</template>

<script>
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-balham.css";
import { AgGridVue } from "ag-grid-vue";

export default {
  name: "FindCourse",
  components: {
    AgGridVue,
  },
  created() {
    this.rowSelection = 'single';
  },
  data() {
    return {
      selectedRow: '',
      columns: [
        {
          field: "name",
          headerName: this.$t('name'),
          filter: true,
        },
        {
          field: "uName",
          headerName: this.$t('ColumnUniversityName'),
          filter: true,
        },
        {
          field: "prevMinScore",
          headerName: this.$t('prevMinScore'),
          filter: 'agNumberColumnFilter',
        },
        {
          field: "budgetPlaces",
          headerName: this.$t('budgetPlaces'),
          width: 170,
          filter: 'agNumberColumnFilter',
        },
        {
          field: "commercePlaces",
          headerName: this.$t('commercePlaces'),
          width: 170,
          filter: 'agNumberColumnFilter',
        },
        {
          field: "planet",
          headerName: this.$t('ColumnUniversityPlanet'),
          filter: true,
        },
        {
          field: "city",
          headerName: this.$t('ColumnUniversityCity'),
          filter: true,

        },
      ],
      rows: [
        {id:0, name: 'ИВТ', uName:'ЛИТИ', prevMinScore: 200, budgetPlaces: 50, commercePlaces: 50, planet: 'Земля', city: 'Санкт-Петербург'},
        {id:1, name: 'УУУ', uName:'БРУЧ', prevMinScore: 220, budgetPlaces: 19, commercePlaces: 19, planet: 'Земля', city: 'Санкт-Петербург'},
        {id:2, name: 'ЫЫЫ', uName:'ИЧМО', prevMinScore: 205, budgetPlaces: 10, commercePlaces: 27, planet: 'Земля', city: 'Санкт-Петербург'},
        {id:3, name: 'ЯЯЯ', uName:'ХИХИ', prevMinScore: 295, budgetPlaces: 2, commercePlaces: 20, planet: 'Марс', city: 'Милки-Вэй'},
      ]
    }
  },
  methods: {
    onSelectionChanged() {
      const selectedRows = this.gridApi.getSelectedRows();
      this.selectedRow = selectedRows[0].id;
    },
    onGridReady(params) {
      this.gridApi = params.api;
      this.gridColumnApi = params.columnApi;
    },
  },
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
.table__container {
  width: 100%;
  display: flex;
  flex-direction: row;
  vertical-align: center;
  justify-content: center;
}

.table-div{
  width: 70%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.table-btns__container {
  width: 100%;
  display: flex;
  flex-direction: row;
  vertical-align: center;
  justify-content: center;
  margin-bottom: 3px;
}
.table-buttons {
  width: 70%;
  display: flex;
  flex-direction: row;
  align-items: flex-end;
  justify-content: flex-end;
}
.table-btn {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.b-table-btn {
  border: 2px solid #04121F;
  border-bottom-left-radius: 20px;
  border-top-right-radius: 20px;
}
.ag-theme-balham-dark {
  --ag-odd-row-background-color: #343a40;
  --ag-header-background-color: #ffc107;
  --ag-header-foreground-color: #343a40;
}
</style>
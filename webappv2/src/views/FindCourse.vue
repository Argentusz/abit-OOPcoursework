<template>
  <div class="findCourse__container">
    <router-link class="backBtn" to="/"><b-icon-arrow-left/> {{$t('back')}}</router-link>
    <div class="title">
      {{$t('findCourse')}}
    </div>
    <div class="table-btns__container">
      <div class="table-buttons">
      <div class="table-btn">
        <b-button
            class="b-table-btn"
            variant="warning"
            @click="goApply()"
        ><b-icon-plus class="mr-2"/>{{$t('applyToUn')}}</b-button>
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
            :localeText="localeText"
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
import AG_GRID_LOCALE_EN from "@/helpers/aggrid-en";
import AG_GRID_LOCALE_RU from "@/helpers/aggrid-ru";
import AG_GRID_LOCALE_IG from "@/helpers/aggrid-ig";
import AG_GRID_LOCALE_PL from "@/helpers/aggrid-pl";
import {url} from "@/main";
import consts from "@/helpers/consts";

export default {
  name: "FindCourse",
  components: {
    AgGridVue,
  },
  created() {
    this.rowSelection = 'single';
  },
  beforeMount() {
    const id = localStorage.getItem('uid')
    this.setAGGridLocale(localStorage.getItem('lang'))
    if (id == null) {
      this.$router.push('/signin')
    }
    this.studentId = id
    this.$http.get(url + "/api/" + consts.apiV + "/courses/").then(
        response=> {
          this.rows = response.data
        })
  },
  data() {
    return {
      selectedRow: '',
      localeText: null,
      studentId: -1,
      columns: [
        {
          field: "name",
          headerName: this.$t('name'),
          filter: true,
          sortable: true,
          width: 200,
        },
        {
          field: "universityName",
          headerName: this.$t('ColumnUniversityName'),
          filter: true,
          sortable: true,
          width: 200,
        },
        {
          field: "prevMinScore",
          headerName: this.$t('prevMinScore'),
          filter: 'agNumberColumnFilter',
          sortable: true,
          width: 150,
        },
        {
          field: "budgetPlaces",
          headerName: this.$t('budgetPlaces'),
          width: 160,
          filter: 'agNumberColumnFilter',
          sortable: true,
        },
        {
          field: "commercePlaces",
          headerName: this.$t('commercePlaces'),
          width: 170,
          filter: 'agNumberColumnFilter',
          sortable: true,
        },
        {
          field: "planet",
          headerName: this.$t('ColumnUniversityPlanet'),
          filter: true,
          sortable: true,
          width: 100,
        },
        {
          field: "city",
          headerName: this.$t('ColumnUniversityCity'),
          filter: true,
          sortable: true,
          width: 142,
        },
        {
          field: "eExamDate",
          headerName: this.$t('ColumnEExamDate'),
          width: 200,
          sortable: true,
        },
        {
          field: "eExamAud",
          headerName: this.$t('ColumnEExamAud'),
          filter: 'agNumberColumnFilter',
          width: 200,
        },
      ],
      rows: []
    }
  },
  methods: {
    goApply() {
      const courseId = this.selectedRow
      this.$http.post(url + "/api/" + consts.apiV + "/students/to_courses/" + this.studentId + '/' + courseId).then(
          response=>{
            this.$bvToast.toast(this.$t('successApply'),
                {
                  title: this.$t('success'),
                  variant: 'success',
                  solid: true,
                  toaster: 'b-toaster-bottom-right'
                }
            )
          }, err => {
            this.$bvToast.toast(this.$t('alreadyApplied'),
                {
                  title: this.$t('error'),
                  variant: 'danger',
                  solid: true,
                  toaster: 'b-toaster-bottom-right'
                }
            )
          }
      )
    },
    onSelectionChanged() {
      const selectedRows = this.gridApi.getSelectedRows();
      this.selectedRow = selectedRows[0].id;
    },
    onGridReady(params) {
      this.gridApi = params.api;
      this.gridColumnApi = params.columnApi;
    },
    setAGGridLocale(lcl) {
      switch (lcl) {
        case 'en':
          this.localeText = AG_GRID_LOCALE_EN;
          break;
        case 'ru':
          this.localeText = AG_GRID_LOCALE_RU;
          break;
        case 'ing':
          this.localeText = AG_GRID_LOCALE_IG;
          break;
        case 'pl':
          this.localeText = AG_GRID_LOCALE_PL;
          break;
        case null:
          this.localeText = AG_GRID_LOCALE_RU;
          break;
      }
    },
  },
}
</script>

<style scoped>
.findCourse__container {
  height: calc(100vh - 45px);
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
  width: 92%;
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
  width: 92%;
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
  --ag-secondary-foreground-color: #343a40;
}
</style>
<template>
  <div class="findUniversity__container">
    <router-link class="backBtn" to="/"><b-icon-arrow-left/> {{$t('back')}}</router-link>
    <div class="title">
      {{$t('findUniversity')}}
    </div>

    <div class="table-btn__container">
      <div class="table-btns">
        <b-button
            class="table-btn"
            variant="warning"
            v-if="showCourseTable === false"
            @click="expand()"
            :disabled="!ableToExpand()">
          <b-icon-chevron-expand/> {{$t('showCourses')}}
        </b-button>
        <b-button
            class="table-btn"
            variant="warning"
            v-if="showCourseTable === true"
            @click="collapse()">
          <b-icon-arrow-return-left/> {{$t('back')}}
        </b-button>

        <b-button
            class="table-btn"
            variant="warning"
            v-if="showCourseTable === true"
            @click="goApply()">
          <b-icon-plus/>{{$t('applyToUn')}}
        </b-button>
      </div>
    </div>

    <div class="table-div">
      <ag-grid-vue
        v-if="!showCourseTable"
        class="ag-theme-balham-dark"
        :columnDefs="columns"
        :rowData="rows"
        :rowSelection="rowSelection"
        @selection-changed="onSelectionChanged()"
        @grid-ready="onGridReady"
        :localeText="localeText"
      />
        <ag-grid-vue
            v-if="showCourseTable"
            class="ag-theme-balham-dark"
            :columnDefs="courseColumns"
            :rowData="courseRows"
            :rowSelection="rowSelection"
            @selection-changed="onCourseSelectionChanged()"
            @grid-ready="onGridReady"
            :localeText="localeText"
        />

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
  name: "FindUniversity",
  data() {
    return {
      columns: [
        {
          field: "name",
          headerName: this.$t('ColumnUniversityName'),
          width: 548,
          filter: true,
        },
        {
          field: "planet",
          headerName: this.$t('ColumnUniversityPlanet'),
          width: 200,
          filter: true,
        },
        {
          field: "city",
          headerName: this.$t('ColumnUniversityCity'),
          width: 400,
          filter: true,
        },
      ],

      courseColumns: [
        {
          field: "name",
          headerName: this.$t('name'),
          filter: true,
        },
        {
          field: "prevMinScore",
          headerName: this.$t('prevMinScore'),
          filter: 'agNumberColumnFilter',
          width: 200,
        },
        {
          field: "budgetPlaces",
          headerName: this.$t('budgetPlaces'),
          width: 200,
          filter: 'agNumberColumnFilter',
        },
        {
          field: "commercePlaces",
          headerName: this.$t('commercePlaces'),
          width: 200,
          filter: 'agNumberColumnFilter',
        },
      ],

      rows: null,
      courseRows: null,
      localeText: null,
      rowSelection: 'single',
      courseIdSelected: null,
      studentId: null,
      modalShow: false,
      showCourseTable: false,
      selectedRow: null,
    }
  },
  components: {
    AgGridVue,
  },
  beforeMount() {
    this.setAGGridLocale(localStorage.getItem('lang'))
    const id = localStorage.getItem('uid')
    if (id == null) {
      this.$router.push('/signin')
    }
    this.studentId = id
    this.$http.get(url + "/api/" + consts.apiV + "/universities/").then(
        response=> {
          console.log(response)
          this.rows = response.data
        })
  },
  methods: {
    goApply() {
      this.$http.post(url + "/api/" + consts.apiV + "/students/to_courses/" + this.studentId + '/' + this.courseIdSelected).then(
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
    ableToExpand() {
      return this.selectedRow !== null
    },
    expand() {
      const selectedRows = this.gridApi.getSelectedRows();
      this.courseRows = selectedRows[0].courses;
      if (this.selectedRow !== null) {
        this.showCourseTable = true;
      }

    },
    collapse() {
      this.showCourseTable = false;
      this.selectedRow = null;
      this.courseIdSelected = null;
    },
    onSelectionChanged() {
      const selectedRows = this.gridApi.getSelectedRows();
      this.selectedRow = selectedRows[0].id;
    },
    onCourseSelectionChanged() {
      const selectedRows = this.gridApi.getSelectedRows();
      this.courseIdSelected = selectedRows[0].id;
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
.table-btn__container {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: center;
}
.table-btns {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  width: 60%;
}
.table-btn {
  border: 2px solid black;
  margin-left: 1px;
  margin-right: 1px;
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
  --ag-header-background-color: #ffc107;
  --ag-header-foreground-color: #343a40;
  --ag-secondary-foreground-color: #343a40;

  width: 60%;
  height: 60vh;
}
</style>

<template>
  <div class="universityPOV__container">
<!--    <div class="universityPOV__top">-->
<!--      <div class="universityProfile">-->
<!--        <b-avatar class="universityAvatarIcon" variant="primary" :text="makeInitials()"/>-->
<!--        {{ universityData.name }}-->
<!--        <b-button variant="danger"-->
<!--                  @click="exit()"-->
<!--                  class="exitBtn">-->

<!--          <b-icon-box-arrow-right class="gearIcon"/>{{$t('exit')}}-->

<!--        </b-button>-->
<!--      </div>-->
<!--      <div class="controlPanelTitle">-->
<!--        {{ $t('controlPanelTitle') }}-->
<!--      </div>-->
<!--    </div>-->
    <div class="universityPOV__bottom">
      <div class="custom-shape-divider-top-1678748417">
        <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
          <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" class="shape-fill"></path>
          <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" class="shape-fill"></path>
          <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" class="shape-fill"></path>
        </svg>
      </div>

      <div class="universityInfo">
        <div class="universityNameAndAvatar">
        <b-avatar style="margin-right:2px" variant="primary" :text="makeInitials()"/>
        {{ universityData.name }}
        </div>
        <b-button variant="danger"
                  @click="exit()"
                  class="exitBtn">
          <b-icon-box-arrow-right class="gearIcon"/>{{$t('exit')}}
        </b-button>
      </div>
      <div v-if="!showApplicants">
      <div class="newCourseBtn">
        <div class="controlPanelTitle">
          {{$t('controlPanelTitle')}}
        </div>
       <div>
        <b-button
            variant="dark"
            class="tableBtn"
            v-b-modal.new-course-modal
            style="z-index: 2;"
        >
          {{ $t('addCourse') }} <b-icon-plus/>
        </b-button>
        <b-button
          variant="warning"
          class="tableBtn"
          @click="goToApplicants()"
          :disabled="selectedRow===''"
        >
          {{ $t('showApplicants')}}  <b-icon-people-fill/>
        </b-button>
        <b-dropdown variant="warning" class="tableBtn" right dropup no-caret>
          <template #button-content>
            <div style="font-size: 12px">
              <b-icon-download style="font-size: 12px"/>
            </div>

          </template>
          <b-dropdown-item-button
              style="font-size:12px;"
              @click="JSONConv()"
          >
            JSON
          </b-dropdown-item-button>
          <b-dropdown-item-button
              style="font-size: 12px"
              @click="PDFConv()"
          >
            PDF
          </b-dropdown-item-button>
        </b-dropdown>
        <b-button
            variant="danger"
            class="tableBtn"
            @click="deleteCourseShow = true"
        >
          <b-icon-trash-fill/>
        </b-button>
        </div>
      </div>
      <div class="table">
        <ag-grid-vue
            style="width: 100%; height: 30vh;"
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
      <div v-if="showApplicants">
        <div class="newCourseBtn">
          <b-button variant="warning"
            @click="goToU"
            class="tableBtn"
          >
            <b-icon-arrow-return-left/> {{$t('back')}}
          </b-button>
          <div class="controlPanelTitle">
            Всего абитуриентов: {{rowsAppl.length}}
          </div>
        </div>
        <div class="table">
          <ag-grid-vue
              style="width: 100%; height: 30vh;"
              class="ag-theme-balham-dark"
              :columnDefs="columnsAppl"
              :rowData="rowsAppl"
              :rowSelection="rowSelection"
              @selection-changed="onSelectionChangedAppl()"
              @grid-ready="onGridReady"
              :localeText="localeText"
              @filter-changed="filterChanged()"
          />
        </div>
        <div class="undertale">
          Отображено абитуриентов: {{applsShown}}
        </div>
      </div>
      <div class="custom-shape-divider-bottom-1683201338">
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
               cancel-variant="dark"
               :cancel-title="$t('cancel')"
               @ok="newCourse"
               :header-bg-variant="'warning'"
               :header-border-variant="'warning'"
               :body-bg-variant="'dark'"
               :body-text-variant="'light'"
               :footer-bg-variant="'dark'"
               :footer-border-variant="'dark'"
      >
        <b-alert variant="danger" :show="newCourseUnfilled">{{$t('error')}}</b-alert>
        {{$t('name')}}
        <b-form-input class="my-2"
                      :placeholder="$t('name')" type="text"
                      v-model="newCourseData.name"
        />
        {{$t('prevMinScore')}}
        <b-form-input class="my-2"
                      :placeholder="$t('prevMinScore')" type="number"
                      v-model="newCourseData.prevMinScore"
        />
        {{$t('budgetPlaces')}}
        <b-form-input class="my-2"
                      :placeholder="$t('budgetPlaces')" type="number"
                      v-model="newCourseData.budgetPlaces"
        />
        {{$t('commercePlaces')}}
        <b-form-input class="my-2"
                      :placeholder="$t('commercePlaces')" type="number"
                      v-model="newCourseData.commercePlaces"
        />
        {{$t('ColumnEExamDate')}}
        <b-form-input class="my-2"
                      :placeholder="$t('ColumnEExamDate') + ' ('+ $t('dateFmt') + ')'"
                      v-model="newCourseData.eExamDate"
        />
        {{$t('ColumnEExamAud')}}
        <b-form-input class="my-2"
                      :placeholder="$t('ColumnEExamAud')" type="number"
                      v-model="newCourseData.eExamAud"
        />

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

      <b-modal
          v-model="deleteCourseShow"
          :title="$t('cancelCourse')"
          :header-bg-variant="'warning'"
          :header-border-variant="'warning'"
          :body-bg-variant="'dark'"
          :body-text-variant="'light'"
          :footer-bg-variant="'dark'"
          :footer-border-variant="'dark'"
          ok-variant="danger"
          cancel-variant="warning"
          :ok-title="$t('delete')"
          :cancel-title="$t('cancel')"
          @ok="deleteCourse()"
      >{{$t('areYouSureDeletingCourseU')}}</b-modal>

    </div>
  </div>
</template>

<script>
import {url} from "@/main";
import consts from "@/helpers/consts"
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-balham.css";
import {AgGridVue} from "ag-grid-vue";
import "@/helpers/aggrid-en"
import "@/helpers/aggrid-ru"
import "@/helpers/aggrid-pl"
import "@/helpers/aggrid-ig"
import AG_GRID_LOCALE_EN from "@/helpers/aggrid-en";
import AG_GRID_LOCALE_RU from "@/helpers/aggrid-ru";
import AG_GRID_LOCALE_IG from "@/helpers/aggrid-ig";
import AG_GRID_LOCALE_PL from "@/helpers/aggrid-pl";
import jsPDF from "jspdf";

export default {
  name: "UniversityPOView",
  components: {
    AgGridVue
  },
  data() {
    return {
      gridApi: null,
      showApplicants: false,
      localeText: null,
      selectedRow: '',
      selectedRowAppl: '',
      applsShown: 0,
      deleteCourseShow: false,
      universityData: {name: null, login: null, id: null},
      columns: [
        {
          field: "name",
          headerName: this.$t('name'),
          filter: true,
          sortable: true,

        },
        {
          field: "prevMinScore",
          headerName: this.$t('prevMinScore'),
          filter: 'agNumberColumnFilter',
          width: 160,
          sortable: true,

        },
        {
          field: "budgetPlaces",
          headerName: this.$t('budgetPlaces'),
          filter: 'agNumberColumnFilter',
          width: 180,
          sortable: true,

        },
        {
          field: "commercePlaces",
          headerName: this.$t('commercePlaces'),
          filter: 'agNumberColumnFilter',
          width: 180,
          sortable: true,

        },
        {
          field: "eExamDate",
          headerName: this.$t('ColumnEExamDate'),
          filter: true,
          width: 160,
          sortable: true,

        },
        {
          field: "eExamAud",
          headerName: this.$t('ColumnEExamAud'),
          filter: 'agNumberColumnFilter',
          width: 257,
          sortable: true,

        },
      ],
      columnsAppl: [
        {
          field: "name",
          headerName: this.$t('fullName'),
          filter: true,
          sortable: true,

        },
        {
          field: "scores.Russian",
          headerName: this.$t('russian'),
          filter: 'agNumberColumnFilter',
          width: 130,
          sortable: true,

        },
        {
          field: "scores.Math",
          headerName: this.$t('math'),
          filter: 'agNumberColumnFilter',
          width: 130,
          sortable: true,

        },
        {
          field: "scores.Ingirmanlandian",
          headerName: this.$t('ingirmanlandian'),
          filter: 'agNumberColumnFilter',
          width: 170,
          sortable: true,

        },
        {
          field: "scores.English",
          headerName: this.$t('english'),
          filter: 'agNumberColumnFilter',
          width: 150,
          sortable: true,

        },
        {
          field: "scores.IT",
          headerName: this.$t('IT'),
          filter: 'agNumberColumnFilter',
          width: 130,
          sortable: true,

        },
        {
          field: "scores.Physics",
          headerName: this.$t('physics'),
          filter: 'agNumberColumnFilter',
          width: 110,
          sortable: true,

        },
        {
          field: "scores.Literature",
          headerName: this.$t('literature'),
          filter: 'agNumberColumnFilter',
          width: 117,
          sortable: true,

        },

      ],
      rows: [],
      rowsAppl: [],
      newCourseUnfilled: false,
      newCourseData: {
        name: null,
        prevMinScore: null,
        budgetPlaces: null,
        commercePlaces: null,
        eExamDate: null,
        eExamAud: null,
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
  },
  created() {
    this.rowSelection = 'single';
  },
  beforeMount() {
    const id = localStorage.getItem('uuid')
    if (id == null) {
      this.$router.push('/signin')
    }

    this.setAGGridLocale(localStorage.getItem('lang'))

    this.$http.get(url + "/api/" + consts.apiV + "/universities/" + id).then(
      response=> {

        this.universityData.name = response.data.name
        this.universityData.login = response.data.login
        this.universityData.id = response.data.id
        this.rows = response.data.courses
      }
    )
  },
  methods: {
    updateRows() {
      this.$http.get(url + "/api/" + consts.apiV + "/universities/" + this.universityData.id).then(
          response=> {
            this.rows = response.data.courses
          }
      )
    },
    filterChanged() {
      this.applsShown = this.gridApi.getDisplayedRowCount()
    },
    goToApplicants() {
      if (this.selectedRow !== '') {
        this.showApplicants = true
        this.$http.get(url + "/api/" + consts.apiV + "/courses/to_students/" + this.selectedRow).then(
            response=>{
              this.rowsAppl = response.data
              this.applsShown = this.rowsAppl.length
            }
        )
      }
    },
    goToU() {
      this.showApplicants = false
      this.gridApi.deselectAll()
      this.selectedRow = ''
    },
    deleteCourse() {
      const cid = this.selectedRow
      this.$http.delete(url + "/api/" + consts.apiV + "/courses/" + cid).then(response=>{this.updateRows()})
    },
    exit() {
      localStorage.clear()
      this.$router.go()
    },
    makeInitials() {
      if (this.universityData.name != null) {
        return this.universityData.name.charAt(0)
      }
      return ''
    },
    newCourse(bvModalEvent) {
      bvModalEvent.preventDefault()
      let xmz = []
      if (this.newCourseData.exams.russian === true) {
        xmz.push('Russian')
      } if (this.newCourseData.exams.math === true) {
        xmz.push('Math')
      } if (this.newCourseData.exams.ingirmanlandian === true) {
        xmz.push('Ingirmanlandian')
      } if (this.newCourseData.exams.english === true) {
        xmz.push('English')
      } if (this.newCourseData.exams.IT === true) {
        xmz.push('IT')
      } if (this.newCourseData.exams.physics === true) {
        xmz.push('Physics')
      } if (this.newCourseData.exams.literature === true) {
        xmz.push('Literature')
      }
      this.$http.post(url + "/api/" + consts.apiV + "/courses/" + this.universityData.id, {
        id: -1,
        name: this.newCourseData.name,
        description: '',
        prevMinScore: this.newCourseData.prevMinScore,
        budgetPlaces: this.newCourseData.budgetPlaces,
        commercePlaces: this.newCourseData.commercePlaces,
        requiredExams: xmz,
        eExamDate: this.newCourseData.eExamDate,
        eExamAud: this.newCourseData.eExamAud
      }).then(
        response=> {
          this.updateRows()
          this.$bvModal.hide('new-course-modal')
        },
        err => {
          this.newCourseUnfilled = true

        }
      )
    },
    PDFConv() {
      let dataStr = ""
      this.rows.forEach(i=>{
        dataStr+=i.name + " " + i.prevMinScore + " " + i.budgetPlaces + " " + i.commercePlaces + " " + i.eExamDate + " " + i.eExamAud + "\r\n\r\n"
      })
      const customFont = consts.font
      const doc = new jsPDF()
      doc.addFileToVFS("customFont.ttf", customFont);
      doc.addFont("customFont.ttf", "customFont", "normal");
      doc.setFont("customFont");
      doc.text(dataStr, 10,10, {lang: 'ru'})
      doc.save("test.pdf")
    },
    JSONConv() {
      const a = document.createElement("a");
      let text = JSON.stringify(this.rows, null, '\t')
      const file = new Blob([text], {type: 'application/json'});
      a.href = URL.createObjectURL(file);
      a.download = "applies.json";
      a.click();
    },
    onSelectionChanged() {
      const selectedRows = this.gridApi.getSelectedRows();
      if (selectedRows[0] !== undefined) {
        this.selectedRow = selectedRows[0].id;
      }
    },
    onSelectionChangedAppl() {
      const selectedRows = this.gridApi.getSelectedRows();
      if (selectedRows[0] !== undefined) {
        this.selectedRowAppl = selectedRows[0].id;
      }
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
  font-size: 22px;
  align-self: center;
}
.universityPOV__bottom {
  padding-top: 30px;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: calc(100vh - 60px - 220px);
}
.table {
  width: calc(100vw - 300px);
}
.newCourseBtn {
  margin-bottom: 20px;
  width: calc(100vw - 300px);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.universityInfo {
  margin-top: 100px;
  width: calc(100vw - 300px);
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-bottom: 10px;
  align-items: center;
  vertical-align: middle;
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
  top: 45px;
  left: 0;
  width: 100%;
  overflow: hidden;
  line-height: 0;
}

.custom-shape-divider-top-1678748417 svg {
  position: relative;
  display: block;
  width: calc(100% + 1.3px);
  height: 130px;
  transform: rotateY(180deg);
}

.custom-shape-divider-top-1678748417 .shape-fill {
  fill: #FFC107;
}

.custom-shape-divider-bottom-1683201338 {
  position: absolute;
  bottom: 10px;
  left: 0;
  width: 100%;
  overflow: hidden;
  line-height: 0;
  transform: rotate(180deg);
}

.custom-shape-divider-bottom-1683201338 svg {
  position: relative;
  display: block;
  width: calc(100% + 1.3px);
  height: 100px;
  transform: rotateY(180deg);
}

.custom-shape-divider-bottom-1683201338 .shape-fill {
  fill: #343A40;
}
.exitBtn {
  display: flex;
  flex-direction: row;
  font-size: 12px;
}
.controlPanelTitle {
  align-self: center;

}
.tableBtn {
  margin-left: 5px;
  font-size: 12px;
}
.ag-theme-balham-dark {
  --ag-odd-row-background-color: #343a40;
  --ag-header-background-color: #ffc107;
  --ag-header-foreground-color: #343a40;

}
.universityNameAndAvatar {
  margin-right: 10px;
}
.gearIcon {
  margin-right: 2px;
}
</style>
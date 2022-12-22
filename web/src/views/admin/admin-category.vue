<template>
  <a-layout style="padding: 24px 0; background: #fff">
    <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
      <a-form
          layout="inline"
          :model="param"
          @finish="handleFinish"
          @finishFailed="handleFinishFailed"
      >
        <a-form-item>
          <a-space size="small">
            <a-button
                type="primary"
                html-type="submit"
                :disabled="param.name === ''"
                @click="handleQuery()"
            >
              查询
            </a-button>
            <a-button
                type="primary"
                html-type="submit"
                @click="add()"
            >
              新增
            </a-button>
          </a-space>
        </a-form-item>
      </a-form>
      <a-table
          :columns="columns"
          :data-source="level1"
          :row-key="record => record.id"
          :loading="loading"
          :pagination="false"
      >
        <template #cover="{ text: cover}">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
            <a-popconfirm
                title="删除后不可恢复，确认删除？"
                ok-text="是"
                cancel-text="否"
                @confirm="handleDelete(record.id)"
            >
              <a-button type="danger">
                删除
              </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
      v-model:visible="modalVisible"
      title="分类表单"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="category" :label-col="{ span: 6}">
      <a-form-item label="名称">
        <a-input v-model:value="category.name" placeholder="input placeholder" />
      </a-form-item>
      <a-form-item label="父分类">
        <a-select
            ref="select"
            v-model:value="category.parent"
        >
          <a-select-option value="0">无</a-select-option>
          <a-select-option v-for="c in level1" :key="c.id" :value="c.id" :disabled="category.id === c.id">{{c.name}}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="顺序">
        <a-input v-model:value="category.sort" placeholder="input placeholder" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>


<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';
import {message} from "ant-design-vue";
import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'AdminCategory',
  setup() {
    const param = ref();
    param.value = {};
    const categorys = ref();
    const loading = ref(false);

    const columns = [
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '父分类',
        key: 'parent',
        dataIndex: 'parent'
      },
      {
        title: '顺序',
        key: 'sort',
        dataIndex: 'sort'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action'}
      }

    ];

    /**
     * 一级分类树，children属性就是二级分类
     * [{
     *    id: "",
     *    name: "",
     *    children: [{
     *      id: "",
     *      name: "",
     *    }]
     * }]
     */
    const level1 = ref(); // 一级分类树，children属性就是二级分类
    /**
     * 数据查询
     */
    const handleQuery = () => {
      loading.value = true;
      axios.get("/category/all").then((response) => {
        loading.value = false;
        const data = response.data;
        if (data.success) {
          categorys.value = data.content;
          console.log("原始数组：", categorys.value);

          level1.value = [];
          level1.value = Tool.array2Tree(categorys.value, 0);
          console.log("属性结构：", level1);
        } else {
          message.error(data.message);
        }

      });
    };

    // -------- 表单 --------
    const category = ref({});
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;
      axios.post("/category/save", category.value).then((response) => {
        modalLoading.value = false;
        const data = response.data; // data = commonResp
        if (data.success) {
          modalVisible.value = false;

          // 重新加载列表
          handleQuery();
        } else {
          message.error(data.message);
        }
      })
    };

    /**
     * 编辑
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      category.value = Tool.copy(record);
    };

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      category.value = {};
    };

    const handleDelete = (id: number) => {
      axios.delete("/category/delete/" + id).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          // 重新加载列表
          handleQuery();
        }
      });
    };


    onMounted(() => {
      handleQuery();
    });

    return {
      //categorys,
      level1,
      columns,
      loading,

      edit,
      add,

      category,
      modalVisible,
      modalLoading,
      handleModalOk,
      handleDelete,
      param,
      handleQuery,


    };
  },
});
</script>
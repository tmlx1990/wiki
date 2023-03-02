<template>
  <a-layout style="padding: 24px 0; background: #fff">
    <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
      <a-form
          layout="inline"
          :model="param"
      >
        <a-form-item>
          <a-input v-model:value="param.loginName" placeholder="登录名">
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-space size="small">
            <a-button
                type="primary"
                html-type="submit"
                @click="handleQuery({page: 1, size: pagination.pageSize})"
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
          :data-source="users"
          :row-key="record => record.id"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
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
      title="用户表单"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="user" :label-col="{ span: 6}">
      <a-form-item label="登录名">
        <a-input v-model:value="user.loginName" :disabled="!!user.id"/>
      </a-form-item>
      <a-form-item label="昵称">
        <a-input v-model:value="user.name" />
      </a-form-item>
      <a-form-item label="密码">
        <a-input v-model:value="user.password" type="textarea" />
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
  name: 'AdminUser',
  setup() {
    const param = ref();
    param.value = {};
    const users = ref();
    const pagination = ref({
      current: 1,
      pageSize: 2,
      total: 0
    });
    const loading = ref(false);

    const columns = [
      {
        title: '登录名',
        dataIndex: 'loginName'
      },
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '密码',
        dataIndex: 'password'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];
    /**
     * 数据查询
     */
    const handleQuery = (params: any) => {
      loading.value = true;
      axios.get("/user/list",{
        params:{
          page: params.page,
          size: params.size,
          loginName: param.value.loginName,
        }
      }).then((resp)=>{
        loading.value = false;
        const  data = resp.data;
        if (data.success){
          users.value = data.content.list;

          //重置分页按钮
          pagination.value.current = params.page;
          pagination.value.total = data.content.total;
        } else {
          message.error(data.message);
        }

      });
    };

    /**
     * 表格点击页面触发
     */
    const handleTableChange = (pagination: any) => {
      console.log("看看自带的分页参数都有什么：" + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    // -------- 表单 --------
    const user = ref();
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;

      axios.post("/user/save",user.value).then((resp)=>{
        const data = resp.data;
        if (data.success){
          modalVisible.value = false;

          //重新加载列表
          handleQuery({
            page:pagination.value.current,
            size:pagination.value.pageSize,
          })
        } else {
          message.error(data.message);
        }
      })
    };

    /**
     * 编辑
     */
    const edit = (record: any) => {
      user.value = Tool.copy(record);
      modalVisible.value =true;
    };

    /**
     * 新增
     */
    const add = () => {
      modalLoading.value = false;
      user.value = {};
      modalVisible.value =true;
    };

    //删除
    const handleDelete = (id:number)=>{
      axios.delete("/user/delete/"+id).then((response)=>{
        const data = response.data;
        if (data.success){
          //重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        } else {
          message.error(data.message);
        }
      })
    }

    onMounted(() => {
      handleQuery({
        page:1,
        size:pagination.value.pageSize
      })
    });

    return {
      param,
      users,
      pagination,
      columns,
      loading,
      handleTableChange,

      edit,
      modalVisible,
      modalLoading,
      handleModalOk,
      user,
      add,
      handleDelete,
      handleQuery,
    };
  },
});
</script>
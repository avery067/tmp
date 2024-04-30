package com.example.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName laboratory_user
 */
@TableName(value ="laboratory_user")
@Data
public class LaboratoryUser implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer laboratoryId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer state;

    /**
     * 
     */
    private Integer sign;

    /**
     * 
     */
    private Date signTime;

    /**
     * 
     */
    private String no;

    /**
     * 超时状态
     */
    private Integer signState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LaboratoryUser other = (LaboratoryUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLaboratoryId() == null ? other.getLaboratoryId() == null : this.getLaboratoryId().equals(other.getLaboratoryId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign()))
            && (this.getSignTime() == null ? other.getSignTime() == null : this.getSignTime().equals(other.getSignTime()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getSignState() == null ? other.getSignState() == null : this.getSignState().equals(other.getSignState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLaboratoryId() == null) ? 0 : getLaboratoryId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
        result = prime * result + ((getSignTime() == null) ? 0 : getSignTime().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getSignState() == null) ? 0 : getSignState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", laboratoryId=").append(laboratoryId);
        sb.append(", createTime=").append(createTime);
        sb.append(", state=").append(state);
        sb.append(", sign=").append(sign);
        sb.append(", signTime=").append(signTime);
        sb.append(", no=").append(no);
        sb.append(", signState=").append(signState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
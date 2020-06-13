package com.atomsk.vhr.mapper;

import com.atomsk.vhr.model.MailSendLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Atomsk
 * @github https://github.com/AtomskGit
 * @blog https://atomskgit.github.io/blog/
 * @date 2020/6/11
 */
@Repository
public interface MailSendLogMapper {
    Integer updateMailSendLogStatus(@Param("msgId") String msgId,
                                    @Param("status") Integer status);

    Integer insert(MailSendLog mailSendLog);

    List<MailSendLog> getMailSendLogsByStatus();

    Integer updateCount(@Param("msgId") String msgId,
                        @Param("date") Date date);
}

package com.madgnome.jira.plugins.jirachievements.utils.initializers;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.user.util.UserUtil;
import com.madgnome.jira.plugins.jirachievements.data.services.IUserWrapperDaoService;

public class UserWrappersInitializer implements ITableInitializer
{
  private final UserUtil userUtil;
  private final IUserWrapperDaoService userWrapperDaoService;

  public UserWrappersInitializer(IUserWrapperDaoService userWrapperDaoService, UserUtil userUtil)
  {
    this.userWrapperDaoService = userWrapperDaoService;
    this.userUtil = userUtil;
  }

  @Override
  public void initialize()
  {
    for (User user : userUtil.getUsers())
    {
      if (userWrapperDaoService.get(user) == null)
      {
        userWrapperDaoService.create(user);
      }
    }
  }
}

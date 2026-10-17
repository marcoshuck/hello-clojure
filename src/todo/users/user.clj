(ns todo.users.user)

(def User
  [:map
   [:user/id :uuid]
   [:user/first_name :string]
   [:user/last_name :string]
   [:user/email :string]
   [:user/create_time :time/instant]
   [:user/update_time :time/instant]
   [:user/delete_time [:maybe :time/instant]]])
# SimpleRxAdnroidDemo
简单的Rx示例

记录RxJava和RxAndroid的使用

参考文章：http://gank.io/post/560e15be2dca930e00da1083

核心代码：

    observer = new Observer<MoviesBean>() {

            @Override
            public void onCompleted() {
                Log.v("way","complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.v("way","onError"+e.getLocalizedMessage());
            }

            @Override
            public void onNext(MoviesBean o) {
                tvResult.setText(o.toString());
            }
        };
        
        
        public void load(View v) {

        NetWork.getService().getTop250(20, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
        

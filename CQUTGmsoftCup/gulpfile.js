/**
 * Created by admin on 2017/3/29.
 */
var gulp = require("gulp");
var sourcemaps = require("gulp-sourcemaps");
var babel = require("gulp-babel");
var gftc  = require('gulp-file-transform-cache');
var browserify = require('gulp-browserify')
var source = require('vinyl-source-stream');
var uglify = require("gulp-uglify");
var gutil = require('gulp-util');
var buffer = require('vinyl-buffer');
var sass = require('gulp-sass');
var jsPath="./src/main/webapp/js/develop/*/*.js";
var cssPath="./src/main/webapp/sass/*/*.scss";

gulp.task('default', ['browserify','sass'],function (){
    gulp.watch(jsPath,["browserify"]);
    gulp.watch(cssPath,["sass"]);
});

gulp.task("babel" ,function () {
    return gulp.src(jsPath)
        .pipe(sourcemaps.init())
        .pipe(gftc({ path: '.babelCache',
            transformStreams: [ babel()]
        }))
        .pipe(sourcemaps.write("."))
        .pipe(gulp.dest("./dist/js/"));
});

gulp.task('browserify',["babel"] ,function() {
    return gulp.src("./dist/js/*/*.js")
        .pipe(sourcemaps.init())
        .pipe(browserify())
        .pipe(sourcemaps.write("."))
        .pipe(gulp.dest("./dist/js/"));
});

gulp.task('sass', function () {
    return gulp.src(cssPath)
        .pipe(sourcemaps.init())
        .pipe(sass().on('error', sass.logError))
        .pipe(sourcemaps.write("."))
        .pipe(gulp.dest('./dist/css/'));
});


